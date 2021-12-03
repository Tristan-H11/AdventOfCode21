import Data.List (transpose, foldl')
import Data.Char (digitToInt)

main = do
  ls <- fmap lines (readFile "Day03/input")
  
  print $ part1 ls
  print $ part2 ls


part1 :: [[Char]] -> Int
part1 xs = (toDec $ epsilon) * (toDec $ gamma) where
  epsilon = calcEpsilon xs
  gamma= calcGamma xs

{-
  Part 2 missing !!
-}

calcGamma :: [[Char]] -> [Int]
calcGamma xs = 
  map (\x -> if(2* countLetters x '1' >= (length x)) then 1 else 0) 
  . transpose $ xs

calcEpsilon :: [[Char]] -> [Int]
calcEpsilon xs = map (\x -> if(x==1) then 0 else 1) $ (calcGamma xs)

countLetters :: String -> Char -> Int
countLetters str c = length $ filter (== c) str  
    
toDec :: [Int] -> Int
toDec = foldl' (\acc x -> acc * 2 + x) 0
