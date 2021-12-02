data Instruction = Forward Int | Up Int | Down Int deriving Show
type Position = (Int, Int, Int) {- Forward, Depth, Aim (for part2) -}

main = do
  ls <- fmap lines (readFile "Day2/input")
  let instructionList = map (parseInstruction . makeTuple) ls
  print $ part1 instructionList
  print $ part2 instructionList


part1 :: [Instruction] -> Int
part1 instruction = forward * down where (forward, down, aim) = foldl updatePart1 (0,0,0) instruction

part2 :: [Instruction] -> Int
part2 instruction = forward * down where (forward, down, aim) = foldl updatePart2 (0,0,0) instruction

updatePart1 :: Position -> Instruction -> Position
updatePart1 (x, y, z) (Forward n) = (x + n, y, z)
updatePart1 (x, y, z) (Down    n) = (x, y + n, z)
updatePart1 (x, y, z) (Up      n) = (x, y - n, z)

updatePart2 :: Position -> Instruction -> Position
updatePart2 (x, y, z) (Forward n) = (x + n, y + z * n, z)
updatePart2 (x, y, z) (Down    n) = (x, y, z + n)
updatePart2 (x, y, z) (Up      n) = (x, y, z - n)

makeTuple :: String -> (String, Int)
makeTuple s = (head str, read $ last str :: Int) where str = words s

parseInstruction :: (String, Int) -> Instruction
parseInstruction (dir, val)
    | dir == "forward" = Forward(val)
    | dir == "up"      = Up(val)
    | dir == "down"    = Down(val)
    | otherwise        = error "WTF happened?"