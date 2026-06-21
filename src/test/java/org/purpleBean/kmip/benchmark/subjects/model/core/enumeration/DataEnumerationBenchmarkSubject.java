package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import lombok.Getter;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;

public class DataEnumerationBenchmarkSubject extends KmipBenchmarkSubject<DataEnumeration> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public DataEnumerationBenchmarkSubject() throws Exception {
        DataEnumeration subject = DataEnumeration.Standard.values()[0].inst();  // TODO: Create a default instance
        initialize(subject, DataEnumeration.class);
    }

    @Override
    public String name() {
        return "DataEnumeration";
    }
}