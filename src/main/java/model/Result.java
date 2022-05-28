package model;

public class Result<T> {

    // hide the private constructor to limit subclass types (Success, Error)
    private Result() {
    }
    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Success<T> success = (Success<T>) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result.Error) {
            Error error = (Error) this;
            return "Error[exception=" + error.getError() + "]";
        }
        return "";
    }

    public static class Success<T> extends  Result{
        private T data;
        public Success(T data) {
            this.data = data;
        }
        public T getData() {
            return data;
        }
        public void setData(T data) {
            this.data = data;
        }

    }

    public static class Error extends Result{
        private String error;
        private int code;

        public Error( int code,String error) {
            this.error = error;
            this.code = code;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }


        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
