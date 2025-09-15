package org.purpleBean.kmip;

/**
 * Thread-local context for KMIP codec operations.
 * Maintains KMIP specification version per thread for proper serialization/deserialization.
 */
public final class KmipContext {
    private static final ThreadLocal<KmipSpec> currentSpec = ThreadLocal.withInitial(() -> KmipSpec.UnknownVersion);

    // Prevent instantiation
    private KmipContext() {
    }

    /**
     * Gets the current KMIP specification for the current thread.
     *
     * @return the current KMIP spec, or UnknownVersion if not set
     */
    public static KmipSpec getSpec() {
        KmipSpec spec = currentSpec.get();
        return spec != null ? spec : KmipSpec.UnknownVersion;
    }

    /**
     * Sets the KMIP specification for the current thread.
     *
     * @param spec the KMIP spec to set (if null, clears the current spec)
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

    /**
     * Executes the given runnable with the specified KMIP spec,
     * ensuring the spec is properly cleaned up afterward.
     *
     * @param spec     the KMIP spec to use
     * @param runnable the code to execute
     */
    public static void withSpec(KmipSpec spec, Runnable runnable) {
        KmipSpec previous = getSpec();
        try {
            setSpec(spec);
            runnable.run();
        } finally {
            if (previous != null) {
                setSpec(previous);
            } else {
                clear();
            }
        }
    }
}
