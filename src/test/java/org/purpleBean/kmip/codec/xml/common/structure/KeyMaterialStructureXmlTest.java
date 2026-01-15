package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.KeyMaterialStructure;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@DisplayName("KeyMaterialStructure XML Serialization Tests")
class KeyMaterialStructureXmlTest extends AbstractXmlSerializationTestSuite<KeyMaterialStructure> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

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
    protected KeyMaterialStructure createVariant() {
        // TODO: Update with different values to test variations
        ActivationDate activationDate = ActivationDate.builder().value(FIXED_TIME.plusDays(1)).build();
        State state = State.Standard.DEACTIVATED.inst();
        return KeyMaterialStructure.of(List.of(activationDate, state));
    }
}