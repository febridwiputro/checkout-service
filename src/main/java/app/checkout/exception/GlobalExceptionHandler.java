package app.checkout.exception;

import app.checkout.dto.BaseResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public Response toResponse(Exception exception) {

        LOG.error("Exception occurred", exception);

        if (exception instanceof ServiceException ex) {
            return Response
                    .status(ex.getStatus())
                    .entity(BaseResponse.error(String.valueOf(ex.getStatus()), ex.getMessage()))
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(BaseResponse.internalServerError(
                        "Unexpected error: " + exception.getMessage()
                ))
                .build();
    }
}
