package org.purplebean.kmip.benchmark;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.benchmark.util.BenchmarkSubjects;

/**
 * JMH benchmark harness delegating to pluggable KmipBenchmarkSubject implementations.
 * Add a new subject by implementing KmipBenchmarkSubject and registering it below.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class KmipSerializationBenchmark {

  // JSON serialize/deserialize
  @Benchmark
  public String jsonSerialize(BenchState s) throws Exception {
    return KmipContext.withSpec(s.impl.getSpec(), () -> {
      try {
        return s.impl.jsonSerialize();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Benchmark
  public Object jsonDeserialize(BenchState s) throws Exception {
    return KmipContext.withSpec(s.impl.getSpec(), () -> {
      try {
        return s.impl.jsonDeserialize();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  // XML serialize/deserialize
  @Benchmark
  public String xmlSerialize(BenchState s) throws Exception {
    return KmipContext.withSpec(s.impl.getSpec(), () -> {
      try {
        return s.impl.xmlSerialize();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Benchmark
  public Object xmlDeserialize(BenchState s) throws Exception {
    return KmipContext.withSpec(s.impl.getSpec(), () -> {
      try {
        return s.impl.xmlDeserialize();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  // TTLV serialize/deserialize
  @Benchmark
  public ByteBuffer ttlvSerialize(BenchState s) throws Exception {
    return KmipContext.withSpec(s.impl.getSpec(), () -> {
      try {
        return s.impl.ttlvSerialize();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Benchmark
  public Object ttlvDeserialize(BenchState s) throws Exception {
    return KmipContext.withSpec(s.impl.getSpec(), () -> {
      try {
        return s.impl.ttlvDeserialize();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  @State(Scope.Thread)
  public static class BenchState {

    private static final Map<String, KmipBenchmarkSubject> REGISTRY = new HashMap<>();
    private static final Collection<KmipBenchmarkSubject> allSubjects;

    static {
      Map<String, KmipBenchmarkSubject> discovered = BenchmarkSubjects.discoverMap();
      if (discovered.isEmpty()) {
        throw new IllegalStateException(
            "No KmipBenchmarkSubject implementations discovered. " +
                "Ensure you have ServiceLoader registrations under " +
                "src/test/resources/META-INF/services/" +
                "org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject");
      }
      REGISTRY.putAll(discovered);
      allSubjects = REGISTRY.values();
      System.out.println("Discovered benchmark subjects: " + REGISTRY.keySet());
    }

    /**
     * The name of the benchmark subject to run.
     * This is intended to be provided by the JMH runner.
     * If not provided, a default subject will be used.
     * See {@link JmhBenchmarkRunner} for how to specify subjects.
     */
    @Param(value = {""})
    public String subject;

    private KmipBenchmarkSubject<?> impl;

    @Setup(Level.Trial)
    public void setup() throws Exception {
      if (subject != null && !subject.isEmpty()) {
        // Run specific subject if specified
        impl = REGISTRY.get(subject);
        if (impl == null) {
          throw new IllegalArgumentException("Unknown subject: " + subject +
              ". Available: " + REGISTRY.keySet());
        }
      } else {
        // If no subject specified, use the first one
        impl = allSubjects
            .iterator()
            .next();
        if (impl == null) {
          throw new IllegalStateException("No benchmark subjects available");
        }
      }
      impl.setup();
    }

    @TearDown(Level.Trial)
    public void tearDown() {
      if (impl != null) {
        impl.tearDown();
      }
    }
  }
}
