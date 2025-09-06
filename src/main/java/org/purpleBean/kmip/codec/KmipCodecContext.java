package org.purpleBean.kmip.codec;

import org.purpleBean.kmip.KmipSpec;

/**
 * Thread-local context for KMIP codec operations.
 * Maintains KMIP specification version per thread for proper serialization/deserialization.
 */
public final class KmipCodecContext {
    private static final ThreadLocal<KmipSpec> currentSpec = ThreadLocal.withInitial(() -> KmipSpec.UnknownVersion);

    /**
     * Gets the current KMIP specification for the current thread.
     *
     * @return the current KMIP spec
     */
    public static KmipSpec getSpec() {
        return currentSpec.get();
    }

    /**
     * Sets the KMIP specification for the current thread.
     *
     * @param spec the KMIP spec to set
     */
    public static void setSpec(KmipSpec spec) {
        if (spec == null) {
            clear();
        } else {
            currentSpec.set(spec);
        }
    }

    /**
     * Clears the current thread's KMIP specification and resets to default (UnknownVersion).
     */
    public static void clear() {
        currentSpec.remove();
        currentSpec.set(KmipSpec.UnknownVersion);
    }
}
