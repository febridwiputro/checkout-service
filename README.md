/># checkout service - documentation

<img width="1574" height="394" alt="image" src="https://github.com/user-attachments/assets/fa74aeef-4983-443a-abb7-474bbbaf3edc" />

## Overview

Sistem checkout marketplace menggunakan **Kogito BPMN** dengan 8 tahapan
proses yang dikelola oleh `CheckoutWorkHandler`.

-   **Process ID:** `checkout_process`\
-   **Type:** Synchronous BPMN Process\
-   **Execution Time:** \< 1 second

------------------------------------------------------------------------

## Process Flow

    START
      │
      ▼
    ┌──────────────────┐
    │ 1. Validate Cart │ ──► Cek cart tidak kosong
    └────────┬─────────┘
             │
             ▼
    ┌──────────────────┐
    │ 2. Check Stock   │ ──► Cek ketersediaan stock
    └────────┬─────────┘
             │
             ▼
    ┌──────────────────┐
    │ 3. Reserve Stock │ ──► Reserve stock dari available
    └────────┬─────────┘
             │
             ▼
    ┌──────────────────┐
    │ 4. Create Order  │ ──► Buat order di database
    └────────┬─────────┘
             │
             ▼
    ┌──────────────────┐
    │ 5. Process       │ ──► Proses payment
    │    Payment       │
    └────────┬─────────┘
             │
             ▼
    ┌──────────────────┐
    │ 6. Confirm       │ ──► Kurangi total stock
    │    Deduction     │
    └────────┬─────────┘
             │
             ▼
    ┌──────────────────┐
    │ 7. Confirm Order │ ──► Update status → CONFIRMED
    └────────┬─────────┘
             │
             ▼
    ┌──────────────────┐
    │ 8. Send          │ ──► Kirim notifikasi email
    │    Notification  │
    └────────┬─────────┘
             │
             ▼
    END

------------------------------------------------------------------------

## Daftar 8 Tasks

  ------------------------------------------------------------------------
  No    Task Name                 Method                    Deskripsi
  ----- ------------------------- ------------------------- --------------
  1     Validate Cart             `validateCart()`          Validasi cart
                                                            tidak kosong

  2     Check Stock               `checkStock()`            Mengecek stock
                                                            untuk setiap
                                                            item

  3     Reserve Stock             `reserveStock()`          Melakukan
                                                            reservasi
                                                            stock

  4     Create Order              `createOrder()`           Membuat order
                                                            baru (status:
                                                            PENDING)

  5     Process Payment           `processPayment()`        Memproses
                                                            pembayaran

  6     Confirm Deduction         `confirmDeduction()`      Mengurangi
                                                            total stock

  7     Confirm Order             `confirmOrder()`          Mengubah
                                                            status order
                                                            menjadi
                                                            CONFIRMED

  8     Send Notification         `sendNotification()`      Mengirim email
                                                            konfirmasi
  ------------------------------------------------------------------------

------------------------------------------------------------------------

### 1. InventoryService

  Method                  Input                 Output    Task
  ----------------------- --------------------- --------- --------
  `checkAvailability()`   productId, quantity   boolean   Task 2
  `reserveStock()`        productId, quantity   void      Task 3
  `confirmDeduction()`    productId, quantity   void      Task 6

### 2. OrderService

  Method             Input                Output        Task
  ------------------ -------------------- ------------- --------
  `createOrder()`    CheckoutRequestDto   OrderEntity   Task 4
  `confirmOrder()`   orderId              void          Task 7

### 3. PaymentService

  Method               Input   Output   Task
  -------------------- ------- -------- --------
  `processPayment()`   \-      void     Task 5

### 4. NotificationService

  Method                 Input         Output   Task
  ---------------------- ------------- -------- --------
  `sendNotification()`   orderNumber   void     Task 8

------------------------------------------------------------------------

## Database Changes

### Reserve Stock (Task 3)

**Before:**

    total_stock: 50
    available_stock: 50
    reserved_stock: 0

**After:**

    total_stock: 50
    available_stock: 48
    reserved_stock: 2

### Confirm Deduction (Task 6)

**Before:**

    total_stock: 50
    available_stock: 48
    reserved_stock: 2

**After:**

    total_stock: 48
    available_stock: 48
    reserved_stock: 0

### Order Status

-   Task 4: Create Order → `PENDING`
-   Task 7: Confirm Order → `CONFIRMED`

------------------------------------------------------------------------

## Test Cases

  Test     Task        Scenario                 Status
  -------- ----------- ------------------------ --------
  Test 1   Task 1      Cart valid               PASS
  Test 2   Task 1      Cart kosong              PASS
  Test 3   Task 2      Stock tersedia           PASS
  Test 4   Task 2      Stock tidak tersedia     PASS
  Test 5   Task 3      Reserve stock berhasil   PASS
  Test 6   Task 4      Order dibuat             PASS
  Test 7   Task 5      Payment berhasil         PASS
  Test 8   Task 1--8   Complete flow            PASS

### Running Tests

    mvn clean test

**Expected Output:**

    [INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
    [INFO] BUILD SUCCESS ✅

------------------------------------------------------------------------

Generated automatically ✔️
