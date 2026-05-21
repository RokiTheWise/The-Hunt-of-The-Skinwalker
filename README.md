# The Hunt of the SkinWalker

A 2-player asymmetric horror game built with Java Swing. One player is a **Hunter** armed with a gun; the other is a **SkinWalker** that can disguise itself as environment props. Players connect over a local network and compete across three timed phases.

## Gameplay

Roles are assigned randomly when both players connect. The game plays out in three back-to-back phases:

| Phase | Duration | Description |
|-------|----------|-------------|
| **Hide** | 15 seconds | The SkinWalker hides while the Hunter waits behind a blackout screen |
| **Hunt** | 60 seconds | The Hunter can shoot; the SkinWalker evades |
| **Revenge** | 30 seconds | The Hunter's gun is empty — the SkinWalker can now attack |

**Win conditions:**
- Hunter wins by shooting the SkinWalker
- SkinWalker wins by surviving until Revenge phase and melee-attacking the Hunter
- Draw if time expires

## Controls

| Input | Hunter | SkinWalker |
|-------|--------|------------|
| `W A S D` | Move | Move |
| `Shift` | Sprint (drains stamina meter) | Sprint |
| `Mouse Click` | Fire bullet (aim at cursor) | Attack (melee, during Revenge phase) |
| `R` | Reload between shots | — |
| `Scroll Wheel` | — | Cycle through prop disguises |

The SkinWalker has 18 prop disguises to cycle through. Scroll up/down to switch between them.

## How to Run

The server and clients run as separate Java processes. Start the server first, then launch two clients.

**Start the server:**
```bash
javac GameServer.java
java GameServer
```

**Start each client (run twice, once per player):**
```bash
javac *.java
java GameStarter
```

Both clients connect to `localhost` on port **45731**. The game starts automatically once both players are connected.

## Project Structure

```
├── GameServer.java       — Authoritative server; relays positions, bullets, and game state
├── GameStarter.java      — Entry point for each client
├── GameFrame.java        — Main game loop, input handling, audio, win/loss detection
├── GameCanvas.java       — Rendering layer (camera, tile drawing, overlays)
├── Player.java           — Abstract base class for both character types
├── Hunter.java           — Hunter sprite with 6-frame walk/idle animations (4 directions)
├── SkinWalker.java       — SkinWalker sprite with 8-frame walk / 7-frame idle + 18 prop forms
├── BulletSprite.java     — Bullet rendering and position tracking
├── Tile.java             — Single map tile data
├── TileManager.java      — Loads and draws the tilemap from text files
├── Countdown.java        — Three-phase game timer (Hide → Hunt → Revenge)
│
├── maps/                 — Tilemap layout files (Map.txt, platform layer, second layer)
├── SkinWalker Sprites/   — Directional walk/idle sprite sheets + attack flash
├── Hunter Sprites/       — Directional walk/idle sprite sheets
├── Prop Sprites/         — 18 prop disguise images for the SkinWalker
├── MISC/                 — Title screen, dark forest overlay, wait screen
├── tile/                 — Individual tile images
└── *.wav                 — Sound effects and background music
```

## Architecture

The server holds the single source of truth for both player positions, bullet state, and attack events. Clients send a comma-delimited string every 25 ms with their position, bullet, movement flags, scroll value, and attack state. The server relays each player's data to the other.

Collision detection runs client-side: the SkinWalker checks if an incoming bullet overlaps its hitbox; the Hunter checks if the SkinWalker's attack rectangle overlaps its position.

## Requirements

- Java 8+
- Both players on the same machine or local network
- Standard Java SE libraries only (Swing, Java Sound, Java2D — no external dependencies)
