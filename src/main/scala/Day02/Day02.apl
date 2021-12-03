⍝ Ganz frech von Defelo geklaut :shrug:
⍝ Habs ja verstanden. Muss vorerst reichen, wenn die Zeit fehlt :P

data ← {a b←' '(≠⊆⊢)⍵⋄a (⍎b)} ¨ ⊃⎕NGET './input' 1

part1 ← ×/⊃{d h←⍵⋄(d+((2⊃⍺)×'d'≡⊃⊃⍺)-((2⊃⍺)×'u'≡⊃⊃⍺)) (h+((2⊃⍺)×'f'≡⊃⊃⍺))}/⌽(⊂(0 0)),data

part2 ← ×/1↓⊃{a h d←⍵⋄(a+((2⊃⍺)×'d'≡⊃⊃⍺)-((2⊃⍺)×'u'≡⊃⊃⍺)) (h+((2⊃⍺)×'f'≡⊃⊃⍺)) (d+((a×2⊃⍺)×'f'≡⊃⊃⍺))}/⌽(⊂(0 0 0)),data