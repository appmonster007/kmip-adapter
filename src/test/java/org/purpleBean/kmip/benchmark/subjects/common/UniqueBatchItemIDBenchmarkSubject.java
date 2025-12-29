package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.UniqueBatchItemID;
import java.nio.ByteBuffer;

public class UniqueBatchItemIDBenchmarkSubject extends KmipBenchmarkSubject<UniqueBatchItemID> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public UniqueBatchItemIDBenchmarkSubject() throws Exception {
        UniqueBatchItemID uniqueBatchItemID = UniqueBatchItemID.of(new byte[]{0x01, 0x02, 0x03});
        initialize(uniqueBatchItemID, UniqueBatchItemID.class);
    }

    @Override
    public String name() {
        return "UniqueBatchItemID";
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