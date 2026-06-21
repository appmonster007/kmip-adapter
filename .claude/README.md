# Claude Code agents and skills for KMIP Adapter

Project-scoped Claude Code configuration. Available to anyone who clones the repo.
Inspired by the BMAD-Method's planning-and-build agent pipeline, adapted to this project's KMIP workflow:
**scrape spec → triage gaps → design type → generate scaffolding → implement → test → register codecs**.

## Agents (`.claude/agents/`)

Specialised roles invoked via `Agent` (subagent delegation). Each owns one stage of the pipeline; together they form a BMAD-style planning-and-build loop tailored to KMIP type work.

| Agent | BMAD analog | Owns |
|---|---|---|
| [kmip-spec-analyst](agents/kmip-spec-analyst.md) | Analyst | Re-scrape OASIS specs, parse `docs/kmip-pending-implementation.md`, surface gaps |
| [kmip-pm](agents/kmip-pm.md) | PM / PO | Bucket and sequence pending work into story cards |
| [kmip-architect](agents/kmip-architect.md) | Architect | Design class shape — tag, fields, supportedVersions, breaking-change handling |
| [kmip-type-developer](agents/kmip-type-developer.md) | Dev | Drive `generate.sh`, fill the stub, follow project conventions |
| [kmip-test-engineer](agents/kmip-test-engineer.md) | QA | Fill test fixtures, use the `KmipSerializationTestSuite` interface family |
| [kmip-codec-registrar](agents/kmip-codec-registrar.md) | Dev (specialist) | Verify/repair META-INF/services SPI entries |

## Skills (`.claude/skills/`)

Self-invocable workflows. Claude triggers them when the user request matches their description.

| Skill | Trigger |
|---|---|
| [kmip-scrape-spec](skills/kmip-scrape-spec/SKILL.md) | "refresh KMIP spec coverage", "regenerate pending doc" |
| [kmip-chunk-spec](skills/kmip-chunk-spec/SKILL.md) | Re-split large spec/pending files into ~500 small per-entity chunks under `docs/kmip-spec/chunks/` (run after `kmip-scrape-spec`) |
| [kmip-implementation-status](skills/kmip-implementation-status/SKILL.md) | "what's missing in KMIP", "show implementation gaps" |
| [kmip-version-audit](skills/kmip-version-audit/SKILL.md) | "audit supportedVersions", "find over-tagged enums", "check version drift" |
| [kmip-add-enum](skills/kmip-add-enum/SKILL.md) | "add KMIP enum X", "implement missing enum Y" |
| [kmip-add-structure](skills/kmip-add-structure/SKILL.md) | "add KMIP structure X", "implement OtpCredential / PasswordCredential / typed-link" |
| [kmip-add-operation](skills/kmip-add-operation/SKILL.md) | "add KMIP operation X", "implement Import / Export / Login" |

## Helper scripts (referenced by skills)

| Script | Purpose |
|---|---|
| `scripts/audit_supported_versions.py` | Diff each enum's `supportedVersions` against spec; powers `kmip-version-audit`. `--fail-on-drift` for CI. |
| `scripts/chunk_kmip_spec.py` | Splits `kmip-all-versions-data.json` and `kmip-pending-implementation.md` into `docs/kmip-spec/chunks/` per-entity files (~520× token savings on lookups). |
| `docs/kmip-spec/scrape_kmip_all_versions.py` | OASIS spec HTML scraper that produces `kmip-all-versions-data.json` and rewrites the pending-implementation doc. |
| `docs/kmip-spec/compare_kmip_enumerations.py` | Cross-version enum CSV diff. |
| `scripts/comprehensive_enum_sync.py` | CSV → Java enum sync (calls `generators/generate.sh`). |
| `scripts/generators/generate.sh` | Unified type generator (enum / datatype / structure with codec + test scaffolding + SPI registration). |

## Typical session shapes

**"What should we work on?"** → `kmip-spec-analyst` (refresh + gaps via chunks) → `kmip-pm` (sequenced backlog).

**"Implement enum X"** → `kmip-architect` (design spec from `chunks/enumerations/<slug>.json`) → invoke `kmip-add-enum` skill → `kmip-version-audit` to confirm new enum at OK status → `kmip-test-engineer` if coverage gaps remain → `kmip-codec-registrar` if SPI grep shows < 7 hits.

**"Add Import operation"** → `kmip-architect` (request and response design) → invoke `kmip-add-operation` skill → `kmip-test-engineer` round-trip integration test.

**"Are the enums spec-correct?"** → invoke `kmip-version-audit` skill (runs in <1s, reports DRIFT / OK / UNMATCHED per class).

**"Re-scrape and re-chunk"** → `kmip-scrape-spec` skill (auto-chains to `kmip-chunk-spec` per its step 5).

## Maintenance

- All agent/skill files validate against the Claude Code frontmatter format (`name:`, `description:` required; `tools:`, `model:` optional on agents).
- When adding a new agent, append its row to the table above. When adding a new skill, create `.claude/skills/<name>/SKILL.md` and register it here.
- `.claude/settings.local.json` (project permissions) is the only other file in this directory; do not commit personal settings.
