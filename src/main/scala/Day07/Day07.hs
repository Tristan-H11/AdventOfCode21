import Data.List.Split
import Data.List

main = do
  ls <- readFile "Day07/input"
  let array::[Int] = sort $ map (read) $ splitOn "," ls
  print $ part1 array
  print $ part2 array

part1::[Int] -> Int
part1 crabs = 
  sum $ map (\crab -> abs(crab-median)) crabs
  where 
    median = crabs!!(length crabs `div`2)

part2::[Int] -> Int
part2 crabs = 
  (minMove crabs `div` 2)

move crabs pos = sum $ map (\x -> let a = abs (x - pos) in a * (a + 1)) crabs

minMove crabs = minimum (map (move crabs) [0..1000])    
