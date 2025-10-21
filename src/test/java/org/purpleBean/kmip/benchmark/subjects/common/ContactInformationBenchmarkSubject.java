package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ContactInformation;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ContactInformationBenchmarkSubject extends KmipBenchmarkSubject<ContactInformation> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

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
