main = do
  ls <- fmap lines (readFile "Day1/input")
  let intList = map (read :: String -> Int) ls
  let pairList = makePairs intList

  print $ part1 pairList
  print $ part2 intList

{-Solutions-}
part1 :: (Num a, Ord a) => [(a, a)] -> Int
part1 xs = length (filter (\(x, y) -> x < y) xs)

part2 :: (Num a, Ord a) => [a] -> Int
part2 xs =
  let tripleSums = map (sum) $ windows3 xs xs xs
   in part1 $ makePairs tripleSums

{-Creates pairs like .sliding(2) in other languages does. [1,2,3,4] => [(1,2),(2,3),(3,4)] -}
makePairs :: [a] -> [(a, a)]
makePairs xs = zip xs (tail xs)

{-Creates pairs like .sliding(3) in other languages does.-}
windows3 :: [a] -> [a] -> [a] -> [[a]]
windows3 (xs) (ys) (zs)
  | length xs > 0 && length ys > 1 && length zs > 2 = [xs !! 0, ys !! 1, zs !! 2] : windows3 (tail xs) (tail ys) (tail zs)
  | otherwise = []