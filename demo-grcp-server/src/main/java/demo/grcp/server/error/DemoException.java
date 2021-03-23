package demo.grcp.server.error;

import org.springframework.core.NestedRuntimeException;

public class DemoException extends NestedRuntimeException {

  /**
   * Construct a {@code NestedRuntimeException} with the specified detail message.
   *
   * @param msg the detail message
   */
  public DemoException(String msg) {
    super(msg);
  }

  /**
   * Construct a {@code NestedRuntimeException} with the specified detail message and nested
   * exception.
   *
   * @param msg the detail message
   * @param cause the nested exception
   */
  public DemoException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
