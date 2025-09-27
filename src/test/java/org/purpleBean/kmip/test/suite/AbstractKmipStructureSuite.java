package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipStructure;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base domain suite for objects implementing KmipStructure.
 * Validates general structure semantics and provides extension points for component checks.
 */
@DisplayName("Abstract KMIP Structure Suite")
public abstract class AbstractKmipStructureSuite<T extends KmipStructure> extends AbstractKmipDataTypeSuite<T> {

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
        List<KmipDataType> values = obj.getValues();
        assertThat(values).isNotNull();
        assertThat(values.size()).isGreaterThanOrEqualTo(expectedMinComponentCount());
        assertThat(values).allSatisfy(v -> assertThat(v).isNotNull());
        // Allow subclasses to add deeper validation
        validateComponents(values);
    }
}
