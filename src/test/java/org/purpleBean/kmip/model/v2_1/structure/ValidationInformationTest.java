package org.purpleBean.kmip.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.type.ValidationLevel;
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMajor;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ValidationInformation Domain Tests")
class ValidationInformationTest extends AbstractKmipStructureTestSuite<ValidationInformation> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<ValidationInformation> type() {
        return ValidationInformation.class;
    }

    @Override
    protected ValidationInformation createDefault() {
        return ValidationInformation.of(
                ValidationAuthorityType.Standard.UNSPECIFIED.inst(),
                ValidationVersionMajor.of(1),
                ValidationType.Standard.UNSPECIFIED.inst(),
                ValidationLevel.of(1));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        // TODO: Set the expected minimum number of components
        return 0;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        // TODO: Validate the components of the structure
        // assertThat(values).hasSize(0);
    }
}