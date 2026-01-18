package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

public class MessageExtensionBenchmarkSubject extends KmipBenchmarkSubject<MessageExtension> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public MessageExtensionBenchmarkSubject() throws Exception {
        MessageExtension subject = MessageExtension.builder()
                .vendorIdentification(VendorIdentification.of("test-vendor"))
                .build();
        initialize(subject, MessageExtension.class);
    }

    @Override
    public String name() {
        return "MessageExtension";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}