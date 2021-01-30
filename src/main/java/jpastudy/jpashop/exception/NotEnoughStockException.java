package jpastudy.jpashop.exception;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(String need_more_stock) {
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }

    public NotEnoughStockException() {
    }
}
