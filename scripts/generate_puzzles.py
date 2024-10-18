import argparse
import json
import subprocess
import sys
from tqdm import tqdm

def generate_puzzle(people):
    cmd = ["/usr/bin/docker", "run", "--rm", "murfffi/zebracli", "generate", "-t", "QUESTION", "-p", str(people)]
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
        elif line.startswith("Question:"):
            puzzle_data["question"] = line.split("Question:")[1].strip()
        elif line.startswith("Answer options:"):
            puzzle_data["answer_options"] = line.split("Answer options:")[1].strip().split(", ")
        elif line.startswith("Answer:"):
            puzzle_data["answer"] = line.split("Answer:")[1].strip()
        elif line.startswith("Seed:"):
            puzzle_data["seed"] = int(line.split(":")[1].strip())
        elif current_section == "facts" and line.strip():
            puzzle_data[current_section].append(line.strip())

    return puzzle_data

def main():
    parser = argparse.ArgumentParser(description="Generate zebra puzzles and save to JSONL file.")
    parser.add_argument("num_puzzles", type=int, help="Number of puzzles to generate")
    parser.add_argument("-p", "--people", type=int, default=4, help="Number of people in each puzzle (default: 4)")
    parser.add_argument("-o", "--output", type=str, default="zebras.jsonl", help="Output file name (default: zebras.jsonl)")
    args = parser.parse_args()

    with open(args.output, "a") as f:
        for _ in tqdm(range(args.num_puzzles), desc="Generating puzzles", unit="puzzle"):
            puzzle = generate_puzzle(args.people)
            if puzzle:
                json.dump(puzzle, f)
                f.write('\n')
            else:
                print("Failed to generate a puzzle. Skipping.", file=sys.stderr)

    print(f"Generated {args.num_puzzles} puzzles and saved to {args.output}")

if __name__ == "__main__":
    main()
