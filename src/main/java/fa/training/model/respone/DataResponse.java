package fa.training.model.respone;

import lombok.Data;

@Data
public class DataResponse {
    private boolean error;
    private String message;
    private Object data;
    public DataResponse(Object object){
        this.data = object;
        this.setMessage("Complete");
    }
    public DataResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public DataResponse() {
    }
}
