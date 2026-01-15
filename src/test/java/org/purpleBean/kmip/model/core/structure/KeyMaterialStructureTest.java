package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.model.core.structure.KeyMaterialStructure;
import org.purpleBean.kmip.model.core.type.ActivationDate;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("KeyMaterialStructure Domain Tests")
class KeyMaterialStructureTest extends AbstractKmipStructureTestSuite<KeyMaterialStructure> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<KeyMaterialStructure> type() {
        return KeyMaterialStructure.class;
    }

    @Override
    protected KeyMaterialStructure createDefault() {
        // TODO: Update with actual default values for your structure
        ActivationDate activationDate = ActivationDate.builder().value(FIXED_TIME).build();
        State state = State.Standard.ACTIVE.inst();
        return KeyMaterialStructure.of(List.of(activationDate, state));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        // TODO: Update with the expected minimum number of components
        return 2;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        // Add assertions for components if desired
        // TODO: Add validation for each component
        // Example:
        assertThat(values.get(0).getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
        assertThat(values.get(1).getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
    }
}