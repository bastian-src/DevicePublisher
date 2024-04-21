let SessionLoad = 1
let s:so_save = &g:so | let s:siso_save = &g:siso | setg so=0 siso=0 | setl so=-1 siso=-1
let v:this_session=expand("<sfile>:p")
silent only
silent tabonly
cd ~/Workspace/Repositories/device-publisher/app/src/main
if expand('%') == '' && !&modified && line('$') <= 1 && getline(1) == ''
  let s:wipebuf = bufnr('%')
endif
let s:shortmess_save = &shortmess
if &shortmess =~ 'A'
  set shortmess=aoOA
else
  set shortmess=aoO
endif
badd +13 res/layout/fragment_resource.xml
badd +25 res/layout/activity_main.xml
badd +37 java/eu/bschmidt/devicepublisher/MainActivity.kt
badd +43 java/eu/bschmidt/devicepublisher/ui/ResourceFragment.kt
badd +1 java/eu/bschmidt/devicepublisher/CellDataViewModel.kt
badd +1 java/eu/bschmidt/devicepublisher/ui/CellFragment.kt
badd +1 res/layout/fragment_cell.xml
badd +453145 ~/.local/state/nvim/lsp.log
badd +1 java/eu/bschmidt/devicepublisher/ui/CellDataFragment.kt
badd +27 res/layout/fragment_cell_data_list_list.xml
badd +85 res/layout/fragment_api_status.xml
badd +25 res/values/styles.xml
badd +58 java/eu/bschmidt/devicepublisher/ui/celldata/CellDataFragment.kt
badd +57 java/eu/bschmidt/devicepublisher/ui/api/APIStatusFragment.kt
badd +16 java/eu/bschmidt/devicepublisher/model/api/APIStatusViewModel.kt
badd +0 java/eu/bschmidt/devicepublisher/model/celldata/CellDataViewModel.kt
badd +0 java/eu/bschmidt/devicepublisher/bak/CellDataFragment.kt
badd +11 res/values/colors.xml
argglobal
%argdel
set stal=2
tabnew +setlocal\ bufhidden=wipe
tabnew +setlocal\ bufhidden=wipe
tabnew +setlocal\ bufhidden=wipe
tabnew +setlocal\ bufhidden=wipe
tabrewind
edit java/eu/bschmidt/devicepublisher/MainActivity.kt
let s:save_splitbelow = &splitbelow
let s:save_splitright = &splitright
set splitbelow splitright
wincmd _ | wincmd |
vsplit
1wincmd h
wincmd w
let &splitbelow = s:save_splitbelow
let &splitright = s:save_splitright
wincmd t
let s:save_winminheight = &winminheight
let s:save_winminwidth = &winminwidth
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
exe 'vert 1resize ' . ((&columns * 135 + 135) / 271)
exe 'vert 2resize ' . ((&columns * 135 + 135) / 271)
argglobal
balt res/layout/activity_main.xml
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 1 - ((0 * winheight(0) + 30) / 60)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 1
normal! 05|
wincmd w
argglobal
if bufexists(fnamemodify("res/layout/activity_main.xml", ":p")) | buffer res/layout/activity_main.xml | else | edit res/layout/activity_main.xml | endif
if &buftype ==# 'terminal'
  silent file res/layout/activity_main.xml
endif
balt res/layout/fragment_resource.xml
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 40 - ((39 * winheight(0) + 30) / 60)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 40
normal! 040|
wincmd w
exe 'vert 1resize ' . ((&columns * 135 + 135) / 271)
exe 'vert 2resize ' . ((&columns * 135 + 135) / 271)
tabnext
edit java/eu/bschmidt/devicepublisher/MainActivity.kt
argglobal
balt res/layout/fragment_resource.xml
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 33 - ((18 * winheight(0) + 30) / 60)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 33
normal! 0
tabnext
edit java/eu/bschmidt/devicepublisher/ui/api/APIStatusFragment.kt
let s:save_splitbelow = &splitbelow
let s:save_splitright = &splitright
set splitbelow splitright
wincmd _ | wincmd |
vsplit
1wincmd h
wincmd w
let &splitbelow = s:save_splitbelow
let &splitright = s:save_splitright
wincmd t
let s:save_winminheight = &winminheight
let s:save_winminwidth = &winminwidth
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
exe 'vert 1resize ' . ((&columns * 135 + 135) / 271)
exe 'vert 2resize ' . ((&columns * 135 + 135) / 271)
argglobal
balt java/eu/bschmidt/devicepublisher/ui/celldata/CellDataFragment.kt
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 53 - ((45 * winheight(0) + 30) / 60)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 53
normal! 037|
wincmd w
argglobal
if bufexists(fnamemodify("java/eu/bschmidt/devicepublisher/bak/CellDataFragment.kt", ":p")) | buffer java/eu/bschmidt/devicepublisher/bak/CellDataFragment.kt | else | edit java/eu/bschmidt/devicepublisher/bak/CellDataFragment.kt | endif
if &buftype ==# 'terminal'
  silent file java/eu/bschmidt/devicepublisher/bak/CellDataFragment.kt
endif
balt java/eu/bschmidt/devicepublisher/ui/api/APIStatusFragment.kt
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 17 - ((16 * winheight(0) + 30) / 60)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 17
normal! 05|
wincmd w
exe 'vert 1resize ' . ((&columns * 135 + 135) / 271)
exe 'vert 2resize ' . ((&columns * 135 + 135) / 271)
tabnext
edit res/layout/fragment_api_status.xml
let s:save_splitbelow = &splitbelow
let s:save_splitright = &splitright
set splitbelow splitright
wincmd _ | wincmd |
vsplit
1wincmd h
wincmd w
wincmd _ | wincmd |
split
1wincmd k
wincmd w
let &splitbelow = s:save_splitbelow
let &splitright = s:save_splitright
wincmd t
let s:save_winminheight = &winminheight
let s:save_winminwidth = &winminwidth
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
exe 'vert 1resize ' . ((&columns * 135 + 135) / 271)
exe '2resize ' . ((&lines * 30 + 31) / 63)
exe 'vert 2resize ' . ((&columns * 135 + 135) / 271)
exe '3resize ' . ((&lines * 29 + 31) / 63)
exe 'vert 3resize ' . ((&columns * 135 + 135) / 271)
argglobal
balt java/eu/bschmidt/devicepublisher/ui/api/APIStatusFragment.kt
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 34 - ((33 * winheight(0) + 30) / 60)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 34
normal! 0
wincmd w
argglobal
if bufexists(fnamemodify("res/values/styles.xml", ":p")) | buffer res/values/styles.xml | else | edit res/values/styles.xml | endif
if &buftype ==# 'terminal'
  silent file res/values/styles.xml
endif
balt res/layout/fragment_api_status.xml
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 25 - ((24 * winheight(0) + 15) / 30)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 25
normal! 042|
wincmd w
argglobal
if bufexists(fnamemodify("res/values/colors.xml", ":p")) | buffer res/values/colors.xml | else | edit res/values/colors.xml | endif
if &buftype ==# 'terminal'
  silent file res/values/colors.xml
endif
balt res/values/styles.xml
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 11 - ((10 * winheight(0) + 14) / 29)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 11
normal! 027|
wincmd w
exe 'vert 1resize ' . ((&columns * 135 + 135) / 271)
exe '2resize ' . ((&lines * 30 + 31) / 63)
exe 'vert 2resize ' . ((&columns * 135 + 135) / 271)
exe '3resize ' . ((&lines * 29 + 31) / 63)
exe 'vert 3resize ' . ((&columns * 135 + 135) / 271)
tabnext
edit java/eu/bschmidt/devicepublisher/model/api/APIStatusViewModel.kt
let s:save_splitbelow = &splitbelow
let s:save_splitright = &splitright
set splitbelow splitright
wincmd _ | wincmd |
vsplit
1wincmd h
wincmd w
let &splitbelow = s:save_splitbelow
let &splitright = s:save_splitright
wincmd t
let s:save_winminheight = &winminheight
let s:save_winminwidth = &winminwidth
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
exe 'vert 1resize ' . ((&columns * 135 + 135) / 271)
exe 'vert 2resize ' . ((&columns * 135 + 135) / 271)
argglobal
balt java/eu/bschmidt/devicepublisher/CellDataViewModel.kt
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 16 - ((15 * winheight(0) + 30) / 60)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 16
normal! 017|
wincmd w
argglobal
if bufexists(fnamemodify("java/eu/bschmidt/devicepublisher/model/celldata/CellDataViewModel.kt", ":p")) | buffer java/eu/bschmidt/devicepublisher/model/celldata/CellDataViewModel.kt | else | edit java/eu/bschmidt/devicepublisher/model/celldata/CellDataViewModel.kt | endif
if &buftype ==# 'terminal'
  silent file java/eu/bschmidt/devicepublisher/model/celldata/CellDataViewModel.kt
endif
balt java/eu/bschmidt/devicepublisher/model/api/APIStatusViewModel.kt
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let &fdl = &fdl
let s:l = 24 - ((23 * winheight(0) + 30) / 60)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 24
normal! 0
wincmd w
exe 'vert 1resize ' . ((&columns * 135 + 135) / 271)
exe 'vert 2resize ' . ((&columns * 135 + 135) / 271)
tabnext 3
set stal=1
if exists('s:wipebuf') && len(win_findbuf(s:wipebuf)) == 0 && getbufvar(s:wipebuf, '&buftype') isnot# 'terminal'
  silent exe 'bwipe ' . s:wipebuf
endif
unlet! s:wipebuf
set winheight=1 winwidth=20
let &shortmess = s:shortmess_save
let &winminheight = s:save_winminheight
let &winminwidth = s:save_winminwidth
let s:sx = expand("<sfile>:p:r")."x.vim"
if filereadable(s:sx)
  exe "source " . fnameescape(s:sx)
endif
let &g:so = s:so_save | let &g:siso = s:siso_save
set hlsearch
doautoall SessionLoadPost
unlet SessionLoad
" vim: set ft=vim :
