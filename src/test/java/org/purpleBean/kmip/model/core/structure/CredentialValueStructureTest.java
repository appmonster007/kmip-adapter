package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.Username;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CredentialValueStructure Domain Tests")
class CredentialValueStructureTest extends AbstractKmipStructureTestSuite<CredentialValueStructure> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<CredentialValueStructure> type() {
        return CredentialValueStructure.class;
    }

    @Override
    protected CredentialValueStructure createDefault() {
        return CredentialValueStructure.builder()
                .value(Username.of("test-value"))
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 1;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(1);
        assertThat(values.getFirst()).isInstanceOf(Username.class);
    }
}