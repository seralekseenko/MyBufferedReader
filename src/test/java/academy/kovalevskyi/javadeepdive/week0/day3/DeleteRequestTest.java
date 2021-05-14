package academy.kovalevskyi.javadeepdive.week0.day3;

import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.*;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import org.junit.jupiter.api.Test;

class DeleteRequestTest extends  AbstractRequestTest {

  
  @Test
  void delete_nothing() {
//    var deleteRqst = assertDoesNotThrow(() -> new DeleteRequest.Builder()
//        .from(csv)
//        .where(new Selector.Builder().fieldName("name").value("Vasyl").build())
//        .build(), "Unexpected exception! It was normal request's creation.");
  }

  @Test
  void delete_first_existing_entry() {
    var selector = new Selector.Builder().fieldName("name").value("Vasyl").build();
    String message = String.format("Incoming Csv:\n%s\nIncoming Selector: %s\n", csv, selector);
    var deleteRqst = assertDoesNotThrow(() -> new DeleteRequest.Builder()
        .from(csv)
        .where(selector)
        .build(), "Unexpected exception! It was normal request's creation.");

    var actual = assertDoesNotThrow(deleteRqst::execute, message);
    var expected = new Csv.Builder()
        .header(header)
        .values(removeLineInValues(0))
        .build();
    assertWithMessage("Incoming Selector: " + selector)
        .that(actual)
        .isEqualTo(expected);
  }
}