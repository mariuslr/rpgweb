     d name            s             20
     d msg             s             50
     c     *entry        plist
     c                   parm                    name
     c                   eval      msg='Hello ' + %trim(name) + '!'
     c                   dsply                   msg
     c                   seton                                        lr
