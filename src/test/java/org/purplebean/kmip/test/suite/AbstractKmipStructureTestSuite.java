package org.purplebean.kmip.test.suite;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipStructure;

/**
 * Base domain suite for objects implementing KmipStructure.
 * Validates general structure semantics and provides extension points for component checks.
 */
@DisplayName("Abstract KMIP Structure Suite")
public abstract class AbstractKmipStructureTestSuite<T extends KmipStructure>
    extends AbstractKmipDataTypeTestSuite<T> {

  /**
   * Minimum number of components expected; default 0.
   */
  protected int expectedMinComponentCount() {
    return 0;
  }

  /**
   * Optional hook to validate individual components.
   */
  protected void validateComponents(List<KmipDataType> values) { /* no-op by default */ }

  @Test
  @DisplayName("Structure: values list present and meets minimal expectations")
  protected void structure_values_presentAndValid() {
    T obj = createDefault();
    List<KmipDataType> values = List.of(obj.getValue());
    assertThat(values).isNotNull();
    assertThat(values.size()).isGreaterThanOrEqualTo(expectedMinComponentCount());
    assertThat(values).allSatisfy(v -> assertThat(v).isNotNull());
    // Allow subclasses to add deeper validation
    validateComponents(values);
  }
}
