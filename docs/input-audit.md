# Input audit

Inventoried all 128 supplied files by SHA256, size and signature. Every member of spacegrunts.dat (3,151 entries) and webcache.zip (9 entries) passed CRC inspection. Detailed private reports are under build/audit and reproducible with tools/inspect_input.py.

Inspected startup, graphics/framebuffer handling, timing, keyboard controls, profile and save methods and optional social integration. Binary assets and unrelated vendor classes were fingerprinted, not individually reverse engineered. Decompiled game classes were used only for private local inspection and are excluded from distribution. No supplied EXE or Windows installer was run.

The original archive already contains Linux ARM64 native libraries. The port uses those directly.
The other Windows installation files and bundled JRE are not used by the port.
