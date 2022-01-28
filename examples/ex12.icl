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