package org.purplebean.kmip.benchmark.subjects.model.core;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.TtlvDataType;
import org.purplebean.kmip.model.core.enumeration.NameType;

/**
 * Benchmark subject for {@link TtlvDataType}.
 */
public class TtlvDataTypeBenchmarkSubject extends KmipBenchmarkSubject<TtlvDataType> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TtlvDataTypeBenchmarkSubject}.
   */
  public TtlvDataTypeBenchmarkSubject() throws Exception {
    var supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    TtlvDataType subject = TtlvDataType.ofStructure(
        KmipTag.register(0x540124, "0x540124", supportedVersions),
        TtlvDataType.ofTextString(
            KmipTag.register(0x540125, "0x540125", supportedVersions),
            "test-value-a"),
        TtlvDataType.ofInteger(
            KmipTag.register(0x540126, "0x540126", supportedVersions),
            100),
        TtlvDataType.ofLongInteger(
            KmipTag.register(0x540127, "0x540127", supportedVersions),
            1000L),
        TtlvDataType.ofBigInteger(
            KmipTag.register(0x540128, "0x540128", supportedVersions),
            BigInteger.valueOf(10000)),
        TtlvDataType.ofEnumeration(
            KmipTag.Standard.NAME_TYPE,
            NameType.Standard.UNINTERPRETED_TEXT_STRING),
        TtlvDataType.ofBoolean(
            KmipTag.register(0x540130, "0x540130", supportedVersions),
            true),
        TtlvDataType.ofByteString(
            KmipTag.register(0x540131, "0x540131", supportedVersions),
            ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03})),
        TtlvDataType.ofDateTime(
            KmipTag.register(0x540132, "0x540132", supportedVersions),
            OffsetDateTime.now()),
        TtlvDataType.ofInterval(
            KmipTag.register(0x540133, "0x540133", supportedVersions),
            3600)
    );
    initialize(subject, TtlvDataType.class);
  }

  @Override
  public String name() {
    return "TtlvDataType";
  }
}
