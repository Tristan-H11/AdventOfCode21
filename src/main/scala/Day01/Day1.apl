part1←{+/2</⍵}
part2←{+/2</3+/⍵}

exampleData←199 200 208 210 200 207 240 269 260 263

part1 exampleData ⍝ => 7
part2 exampleData ⍝ => 5