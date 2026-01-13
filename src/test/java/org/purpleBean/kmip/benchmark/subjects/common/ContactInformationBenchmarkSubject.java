package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ContactInformation;

public class ContactInformationBenchmarkSubject extends KmipBenchmarkSubject<ContactInformation> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public ContactInformationBenchmarkSubject() throws Exception {
        var fixed = "test";
        ContactInformation contactInformation = ContactInformation.builder().value(fixed).build();
        initialize(contactInformation, ContactInformation.class);
    }

    @Override
    public String name() {
        return "ContactInformation";
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