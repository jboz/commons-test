package ch.mobi.posi.common.tools;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utilisataire sur les nombre.
 * 
 * @author jboz
 */
public final class NumberUtils extends org.apache.commons.lang.math.NumberUtils {
  private NumberUtils() {
  }

  /**
   * Thread safe date/time formatter.<br>
   * Each thread gets it's own instance of the formatter, which is maintained for the lifetime of the thread.
   */
  protected static ThreadLocal<NumberFormat> formatter = new ThreadLocal<NumberFormat>() {

    // use inner class to initialise instance per thread
    @Override
    protected NumberFormat initialValue() {
      DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
      otherSymbols.setDecimalSeparator('.');
      
      final DecimalFormat decimalFormat = new DecimalFormat("#.##", otherSymbols);
      
      return decimalFormat;
    }
  };

  /**
   * @return thread safe date formatter
   */
  public static final NumberFormat getFormatter() {
    return formatter.get();
  }

  /**
   * @return number formatted
   */
  public static String toString(final Number number) {
    if (number == null) {
      return null;
    }
    return getFormatter().format(number);
  }

  /**
   * @return the smallest value of type double.
   */
  public static String getDecimalMinValue() {
    return Double.toString(Double.MIN_VALUE);
  }
}
