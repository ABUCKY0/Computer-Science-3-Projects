,---------------------------------------.---------.
|  0    1   2   3    4     5    6    7  | 8    9  |
|    ,-----------------------------.    |    .    |
| 10 | 11   12   13   14   15  16  | 17 | 18 | 19 |
|    |    ,-------------------.    |    |    |    |
| 20 | 21 | 22   23   24   25 | 26 | 27 | 28 | 29 |
|    |    `----     ,----     |    |    |    |    |
| 30 | 31   32   33 | X    35 | 36 | 37 | 38 | 39 |
|    |    ,---------"---------:    |    `----'    |
| 40 | 41 | 42   43   44   45 | 46 | 47   48   49 |
|    `----:    ,---------.    |    `---------.    |
| 50   51 | 52 | 53   54 | 55 | 56   57   58 | 59 |
|    .    |    |    .    |    |     ---------'    |
| 60 | 61 | 62 | 63 | 64 | 65 | 66   67   68   69 |
:----'    |    |    |    |    |    ,--------------:
| 70   71 | 72 | 73 | 74 | 75 | 76 | 77   78   79 |
|    .    |    `----'    |    |    |     ----.    |
| 80 | 81 | 82   83   84 | 85 | 86 | 87   88 | 89 |
|    `----"---------     |    |    `---------'    |
| 90   91   92   93   94 | 95 | 96   97   98   99 |
`------------------------'    `-------------------'


 0,  1,  2,  3,  4,  5,  6,  7,  8,  9,
10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
80, 81, 82, 83, 84, 85, 86, 87, 88, 89,
90, 91, 92, 93, 94, 95, 96, 97, 98, 99

100 = the hallway

 n 
w e
 s


(RoomNumAsString, Description,    n,  s,  w,  e)
this.mazemap.add(new Room( 0,"",  -1, 10, -1,  1,emptymazelist))
this.mazemap.add(new Room( 1,"",  -1, -1, 0,   2,emptymazelist))
this.mazemap.add(new Room( 2,"",  -1, -1, 1,   3,emptymazelist))
this.mazemap.add(new Room( 3,"",  -1, -1, 2,   4,emptymazelist))
this.mazemap.add(new Room( 4,"",  -1, -1, 3,   5,emptymazelist))
this.mazemap.add(new Room( 5,"",  -1, -1, 4,   6,emptymazelist))
this.mazemap.add(new Room( 6,"",  -1, -1, 5,   7,emptymazelist)) 
this.mazemap.add(new Room( 7,"",  -1,  17, 6, -1,emptymazelist)) 
this.mazemap.add(new Room( 8,"",  -1, 18, -1,  9,emptymazelist))
this.mazemap.add(new Room( 9,"",  -1, 19,  8, -1,emptymazelist))
this.mazemap.add(new Room( 10,"",  0, 20, -1, -1,emptymazelist))
this.mazemap.add(new Room( 11,"", -1, 21, -1, -1,emptymazelist))
this.mazemap.add(new Room( 12,"", -1, -1, 11, 13,emptymazelist))
this.mazemap.add(new Room( 13,"", -1, -1, 12, 14,emptymazelist))
this.mazemap.add(new Room( 14,"", -1, -1, 13, 15,emptymazelist))
this.mazemap.add(new Room( 15,"", -1, -1, 14, 16,emptymazelist))
this.mazemap.add(new Room( 16,"", -1, 26, 15, -1,emptymazelist))
this.mazemap.add(new Room( 17,"",  7, 27, -1, -1,emptymazelist))
this.mazemap.add(new Room( 18,"",  8, 28, -1, -1,emptymazelist))
this.mazemap.add(new Room( 19,"",  9, 29, -1, -1,emptymazelist))
this.mazemap.add(new Room( 20,"", 10, 30, -1, -1,emptymazelist))
this.mazemap.add(new Room( 21,"", 11, 31, -1, -1,emptymazelist))
this.mazemap.add(new Room( 22,"", -1, -1, -1, 23,emptymazelist))
this.mazemap.add(new Room( 23,"", -1, 33, 22, 24,emptymazelist))
this.mazemap.add(new Room( 24,"", -1, -1, 25, 23,emptymazelist))
this.mazemap.add(new Room( 25,"", -1, 35, 24, -1,emptymazelist))
this.mazemap.add(new Room( 26,"", 16, 36, -1, -1,emptymazelist))
this.mazemap.add(new Room( 27,"", 17, 37, -1, -1,emptymazelist))
this.mazemap.add(new Room( 28,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 29,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 30,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 31,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 32,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 33,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 34,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 35,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 36,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 37,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 38,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 39,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 40,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 41,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 42,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 43,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 44,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 45,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 46,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 47,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 48,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 49,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 50,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 51,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 52,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 53,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 54,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 55,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 56,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 57,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 58,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 59,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 60,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 61,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 62,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 63,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 64,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 65,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 66,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 67,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 68,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 69,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 70,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 71,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 72,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 73,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 74,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 75,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 76,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 77,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 78,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 79,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 80,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 81,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 82,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 83,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 84,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 85,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 86,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 87,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 88,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 89,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 90,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 91,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 92,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 93,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 94,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 95,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 96,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 97,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 98,"", , , , ,emptymazelist))
this.mazemap.add(new Room( 99,"", , , , ,emptymazelist))