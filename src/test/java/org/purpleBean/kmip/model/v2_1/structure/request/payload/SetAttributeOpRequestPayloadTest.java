package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SetAttributeOpRequestPayload Domain Tests")
class SetAttributeOpRequestPayloadTest extends AbstractKmipStructureTestSuite<SetAttributeOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<SetAttributeOpRequestPayload> type() {
        return SetAttributeOpRequestPayload.class;
    }

    @Override
    protected SetAttributeOpRequestPayload createDefault() {
        return SetAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("set-attr-uid-1").build())
                .newAttribute(NewAttribute.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build())
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        return 1;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSizeGreaterThanOrEqualTo(1);
        assertThat(values).anyMatch(v -> v instanceof NewAttribute);
    }
}
