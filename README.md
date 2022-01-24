# ProjetoICL

Este projeto foi desenvolvido no âmbito da cadeira Interpretação e Compilação de Linguagens no ano
lectivo 2021-2022, pelos alunos:<p>

* Gonçalo Martins Lourenço nº55780<p>
* Joana Soares Faria nº55754<p>

O projeto tem duas partes principais: um interpretador e um compilador.<p>
O **interpretador** pode ser corrido através do ficheiro bat `interpreter.bat`, em windows, ou
correr o script `interpreter.sh`, em linux. Quando o programa começar a correr, aparecendo a
prompt `>`, a expressão a interpretar deve ser introduzida. <p>
Para correr o **compilador** primeiro deve-se colocar a expressão a compilar no
ficheiro `source.icl` e de seguida executar o ficheiro bat `compiler.bat`, em windows, ou correr o
script `compiler.sh`, em linux. Os ficheiros gerados com o código JVM poderão ser encontrados na
pasta `generated_files`. A seguir apresentam-se uma série de expressões utilizadas para testar o
projeto:

## Exemplo 1

```
println
    def 
        x = 2 
        y= x+2 
    in 
        def 
            z=3 
        in 
            def 
                y=x+1 
            in 
                x+y+z 
            end 
        end 
    end;;
```

Resultado: `8`

## Exemplo 2

```
println 
    def 
        x = 2 
    in 
        def 
            y = def 
                    x=x+1 
                in 
                    x+x 
                end 
        in 
            x*y 
        end 
    end;;

```

Resultado: `12`

## Exemplo 3

```
println 
    def
        x=2
        y=3
    in
        def
            k=x+y
        in
            x+y+k
        end
    end;;
```

Resultado: `10`

## Exemplo 4

```
println 
    def 
        x = true 
    in 
        if x 
        then 1 
        else 2 end 
    end;;
```

Resultado: `1`

## Exemplo 5

```
println 
    def 
        a = new 5 
    in 
        a := !a + 1; 
        println !a 
    end;;
```

Resultado: `6`

## Exemplo 6

```
def 
    x = new 10 
    s = new 0 
in
    while !x>0 do 
        s := !s + !x ; 
        x := !x - 1 
    end;
    println !s
end;;
```

Resultado: `55`

## Exemplo 7

```
def
  f:(int)int = fun x:int -> x+1 end
in
  println f(2)+f(3)
end;;
```

Resultado: `7`

## Exemplo 8

```
def
  f:(int)int = fun x:int -> if x==0 
                            then 1 
                            else x*f(x-1) end 
                end
in
  println f(3)
end;;
```

Resultado: `6`

## Exemplo 9

```
def
  f:((int)int)int = fun g:(int)int -> g (10) end
in
  def 
    x:int = f(fun y:int -> y*2 end)
  in
   println x+2
  end
end;;
```

Resultado: `22`

## Exemplo 10

```
def
    f:(int, int)int = fun x:int, y:int -> x + y end
in
    println f(2,3)
end;;
```

Resultado: `5`

## Exemplo 11

```
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
```

## Exemplo 12

```
def
    glo : ref int = new 0
in
    def
        f : (int)int= fun n:int -> glo := !glo + n end
    in
        f(2);
        f(3);
        f(4);
        println !glo
    end
end;;
```

Resultado: `9`

## Exemplo 13

```
println def
    f : (int, int)int = fun n:int, b:int ->
        def
            x : ref int = new n
            s : ref int = new b
        in
            while !x > 0 do
                s := !s + !x; x := !x - 1
            end;
            !s
        end
    end
in
    f(10, 0) + f(100, 20)
end;;
```

Resultado: `5125`

## Exemplo 13

```
def
    mod : (int, int)int = fun dividend:int, divisor:int ->
        dividend - divisor * (dividend / divisor)
    end

    seed : ref int = new 2

    random : ()int = fun ->
        seed := mod(8121 * !seed + 28411, 181); !seed
    end

    isinsidecircle : (int, int)bool = fun x:int, y:int ->
        (x * x) + (y * y) <= 32767
    end
in
    def
        i : ref int = new 0
        s : ref int = new 0
    in
        while !i < 30000 do
            def
                x : int = random()
                y : int = random()
            in
                if isinsidecircle(x, y) then
                    s := !s + 1
                else
                    !s
                end;
                i := !i + 1
            end
        end;
        println 4 * !s * 100 / 30000
    end
end;;
```

Resultado: `315`