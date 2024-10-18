import argparse
import json
import subprocess
import sys

def generate_puzzle():
    cmd = ["/usr/bin/docker", "run", "--rm", "murfffi/zebracli", "generate", "-t", "BASIC"]
    result = subprocess.run(cmd, capture_output=True, text=True)
    if result.returncode != 0:
        print(f"Error generating puzzle: {result.stderr}", file=sys.stderr)
        return None

    output = result.stdout.strip()
    puzzle_data = {}

    current_section = None
    for line in output.split('\n'):
        if line.startswith("Facts:"):
            current_section = "facts"
            puzzle_data[current_section] = []
        elif line.startswith("Solution:"):
            current_section = "solution"
            puzzle_data[current_section] = []
        elif line.startswith("Seed:"):
            puzzle_data["seed"] = int(line.split(":")[1].strip())
        elif current_section and line.strip():
            puzzle_data[current_section].append(line.strip())

    return puzzle_data

def main():
    parser = argparse.ArgumentParser(description="Generate zebra puzzles and save to JSONL file.")
    parser.add_argument("num_puzzles", type=int, help="Number of puzzles to generate")
    args = parser.parse_args()

    with open("zebras.jsonl", "a") as f:
        for _ in range(args.num_puzzles):
            puzzle = generate_puzzle()
            if puzzle:
                json.dump(puzzle, f)
                f.write('\n')
            else:
                print("Failed to generate a puzzle. Skipping.", file=sys.stderr)

    print(f"Generated {args.num_puzzles} puzzles and saved to zebras.jsonl")

if __name__ == "__main__":
    main()
