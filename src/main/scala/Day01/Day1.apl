part1←{+/2</⍵}
part2←{+/2</3+/⍵}

data← ⍎¨⊃⎕NGET'./input' 1

part1 data ⍝ Evaluates Part 1 with the input .txt
part2 data ⍝ Evaluates Part 2 with the input .txt
