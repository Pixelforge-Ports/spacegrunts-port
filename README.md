## Notes

Thanks to [Orangepixel](https://orangepixel.net/) for creating **Space Grunts**. Explore hostile space facilities and use weapons and items in turn-based tactical battles.

Porter: **Pixelforge ports (Ronax)**.

This universal **SpaceGrunts.zip** is a bring-your-own-data PortMaster package for compatible
**64-bit ARM Linux firmware**. Supply the owned game files below. PortMaster provides Java 17
and Westonpack. Device testing is requested for muOS on RG34XX SP and other RGXX models,
R36S with compatible firmware, and other ARM64 handhelds. A device name alone does not guarantee
a compatible 64-bit userspace or graphics driver.

## Get spacegrunts.dat from GOG

1. Open [Space Grunts on GOG](https://www.gog.com/en/game/space_grunts) in your owned library and download the **full Windows offline backup installer** for the supported build (1.7.5). Download every accompanying `.bin` part, if listed, and keep them beside the `.exe`. Use the full installer, not a patch or the Galaxy installer.
2. Run the installer on Windows and open the installed game directory. Find `spacegrunts.dat` beside the game executable. Enable file extensions in Explorer so its name is visible.
3. Copy that file unchanged into the installed port at `<ports directory>/spacegrunts/spacegrunts.dat`. Keep its exact name, capitalization and spaces. The Windows EXE and bundled Windows Java runtime are not needed.

Alternatively, extract your full offline installer using [innoextract](https://constexpr.org/innoextract/).
Run `innoextract -d extracted "your-full-offline-installer.exe"`, then locate `spacegrunts.dat`
inside the extracted files (usually `extracted/app/`) and copy it to the same destination.
Installer layouts vary; use Windows installation if your extractor cannot read that installer.
Do not unpack or rename the game archive itself.

No game-data conversion is required on a PC or handheld. The handheld verifies the supplied
archive and creates its save folders. It cannot generate the purchased game data from nothing.

Supported archive SHA-256 (`spacegrunts.dat`):

```text
bf9ffdd25dd9ea58f29247bf7a48c0be6ccec513344c9623eb6ce2c506074016
```

Compare it with `Get-FileHash -Algorithm SHA256 "spacegrunts.dat"` in PowerShell,
or `sha256sum "spacegrunts.dat"` on Linux. A different build needs a compatibility check.

## Installation

1. Update PortMaster. Put **SpaceGrunts.zip** in PortMaster's `autoinstall/` directory, then open PortMaster to install it. Connect to the network to download Java 17 and Westonpack if they are not installed yet.
2. Copy the owned file to **`<ports directory>/spacegrunts/spacegrunts.dat`**.
3. Launch **Space Grunts** from your firmware's ports menu.

For manual installation on **muOS**, extract the ZIP on your computer and copy `Space Grunts.sh`
to `<SD card>/roms/PORTS/`, and the `spacegrunts/` folder to `<SD card>/ports/` on the card
configured as the firmware's ports location. The required file is
**`<SD card>/ports/spacegrunts/spacegrunts.dat`**.

For **ArkOS/dArkOS and standard ports layouts**, extract the ZIP into your configured
ports directory (for example `/roms/ports/` or `/roms2/ports/`) so `Space Grunts.sh` and
`spacegrunts/` are beside each other. Use the firmware's configured ports location; the launcher
uses PortMaster's `directory` value. Avoid creating an extra `SpaceGrunts/` wrapper folder.

## Controls

| Control | Keyboard input / action |
|---|---|
| D-pad up | UP / Up / navigate |
| D-pad down | DOWN / Down / navigate |
| D-pad left | LEFT / Left / navigate |
| D-pad right | RIGHT / Right / navigate |
| A | X / Action / confirm |
| B | ESC / Back / pause |
| X | I |
| Y | M |
| L1 | 1 |
| R1 | 2 |
| L2 | 3 |
| R2 | 4 |
| Start | O / Options |
| Select | ESC / Back / pause |
| Left stick | Same directions as the D-pad |
| Select + Start | Exit; save through the game first |

Use the game's default keyboard bindings. Start sends **O** for options.

## Controller support

All input is supplied through PortMaster's gptokeyb2 and the shipped `.ini` mapping.
Update PortMaster before installing. Native Xbox 360 emulation is not enabled in this
host: its native controller path is disabled. The mapper's `-x` mode replaces keyboard
and mouse output and requires a working native controller backend in the game.
Do not add `-x` to this launcher; it would bypass the controls listed above.

## Display

The display helper accepts 640x480, 720x480, 720x720, 1024x768 and 1280x720, and other
valid dimensions supplied by PortMaster. The host preserves the game view's aspect ratio;
black borders may appear. This includes RG35XX/RG40XX/R36S, RG34XX/SP, CubeXX, TrimUI Brick
and Smart Pro display shapes when their firmware and hardware meet the runtime requirements.

If automatic detection is incorrect, create `spacegrunts/resolution.txt` containing the actual
size, for example `720x480`. Use `auto` or remove the file to restore automatic detection.

## Saves and troubleshooting

Back up **`spacegrunts/saves/`** before updates.
Read **`spacegrunts/log.txt`** if startup fails. Report your device, exact firmware version,
resolution and steps to reproduce, and attach the log. Test menu navigation, gameplay,
audio, game speed, save/reload, suspend/resume and clean exit. Keep purchased game files private.

## Licenses

The original port and host use the MIT license; their separate notices and the gptokeyb
GPL license are in `spacegrunts/licenses/`. Upstream copyright notices remain intact.
The game and screenshot retain Orangepixel's rights. Java, Westonpack and the mapper are installed separately by PortMaster.

## Build the PortMaster package

Requires Python 3.9+ and JDK 17 or newer. **No purchased JAR or DAT is required to compile
the host or build the ZIP.** From this source directory, on Windows:

```bat
python tools/build.py --jdk "C:\Program Files\Java\jdk-17"
```

Replace the quoted path with your installed JDK directory, for example `jdk-26.0.2.1`.
Use double quotes in Windows Command Prompt. On Linux:

```sh
python3 tools/build.py --jdk "/path/to/installed/jdk-17"
```

The first build downloads checksum-pinned public compile dependencies. Later builds may add
`--offline` to use the cache. Handwritten `compile-api/` declarations are compile-only;
only `org/portmaster/spacegrunts/` host classes go into `spacegrunts-host.jar`.


The only release artifact is **`dist/SpaceGrunts.zip`**, a universal BYO-data ZIP.
The build also prepares **`ports/spacegrunts/`** in the PortMaster source submission layout.
It never packages the owned game archive, MewnBase data, Windows runtimes or personal saves.
After editing package documentation or controls, rebuild with:

```sh
python tools/build.py --package-only
python tools/verify_package.py
```

`--package-only` requires a previously built host. The optional `--game-jar` argument checks
a supplied archive's fingerprint; it does not participate in compilation. Downloading a
public compile dependency does not supply the commercial game. Copy the owned files after installing.

Run `bash tests/verify_display.sh` for display-helper checks. Run `python tests/verify_launcher.py` for lifecycle checks. These tests use
mock runtimes and do not mount or run games. See `VALIDATION.md` for the recorded checks
and `testing_thread.txt` for the Discord testing post. Upload source files using Git;
`build/`, `dist/`, generated `ports/` and owned data are excluded by `.gitignore`.

The Discord draft stays in source `testing_thread.txt`; it is not installed by the ZIP.
