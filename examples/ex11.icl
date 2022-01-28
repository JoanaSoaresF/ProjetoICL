def
   m:ref int = new 5666
in
  while !m > 1 do
    if (2*(!m / 2) == !m) then
     m := !m / 2
    else
     m:= 3*!m+1
    end;
    println !m
  end
end;;