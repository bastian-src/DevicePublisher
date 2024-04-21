let SessionLoad = 1
let s:so_save = &g:so | let s:siso_save = &g:siso | setg so=0 siso=0 | setl so=-1 siso=-1
let v:this_session=expand("<sfile>:p")
silent only
silent tabonly
cd ~/Workspace/Repositories/device-publisher/app/src
if expand('%') == '' && !&modified && line('$') <= 1 && getline(1) == ''
  let s:wipebuf = bufnr('%')
endif
let s:shortmess_save = &shortmess
if &shortmess =~ 'A'
  set shortmess=aoOA
else
  set shortmess=aoO
endif
badd +13 main/res/layout/fragment_resource.xml
badd +34 main/res/layout/activity_main.xml
badd +10 main/java/eu/bschmidt/devicepublisher/MainActivity.kt
badd +43 main/java/eu/bschmidt/devicepublisher/ui/ResourceFragment.kt
badd +24 main/java/eu/bschmidt/devicepublisher/CellDataViewModel.kt
badd +1 main/java/eu/bschmidt/devicepublisher/ui/CellFragment.kt
badd +9 main/res/layout/fragment_cell.xml
badd +453145 ~/.local/state/nvim/lsp.log
badd +1 main/java/eu/bschmidt/devicepublisher/ui/CellDataFragment.kt
badd +75 main/java/eu/bschmidt/devicepublisher/ui/CellDataListFragment.kt
badd +49 main/java/eu/bschmidt/devicepublisher/ui/MyCellDataFragmentRecyclerViewAdapter.kt
badd +9 main/res/layout/fragment_cell_data_list_list.xml
badd +1 main/java/eu/bschmidt/devicepublisher/ui/celldata/CellDataRecyclerViewAdapter.kt
badd +1 main/java/eu/bschmidt/devicepublisher/ui/celldata/CellDataFragment.kt
badd +1 main/java/eu/bschmidt/devicepublisher/model/celldata/CellDataViewModel.kt
badd +4 main/java/eu/bschmidt/devicepublisher/api/celldata/CellDataAPI.kt
badd +5 main/java/eu/bschmidt/devicepublisher/api/APIHandler.kt
badd +22 main/res/layout/fragment_api_status.xml
argglobal
%argdel
set stal=2
tabnew +setlocal\ bufhidden=wipe
tabnew +setlocal\ bufhidden=wipe
tabnew +setlocal\ bufhidden=wipe
tabnew +setlocal\ bufhidden=wipe
tabnew +setlocal\ bufhidden=wipe
tabrewind
edit main/java/eu/bschmidt/devicepublisher/api/APIHandler.kt
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
exe 'vert 1resize ' . ((&columns * 136 + 136) / 272)
exe 'vert 2resize ' . ((&columns * 135 + 136) / 272)
argglobal
balt main/java/eu/bschmidt/devicepublisher/api/celldata/CellDataAPI.kt
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
let s:l = 5 - ((4 * winheight(0) + 29) / 59)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 5
normal! 065|
wincmd w
argglobal
if bufexists(fnamemodify("main/java/eu/bschmidt/devicepublisher/api/celldata/CellDataAPI.kt", ":p")) | buffer main/java/eu/bschmidt/devicepublisher/api/celldata/CellDataAPI.kt | else | edit main/java/eu/bschmidt/devicepublisher/api/celldata/CellDataAPI.kt | endif
if &buftype ==# 'terminal'
  silent file main/java/eu/bschmidt/devicepublisher/api/celldata/CellDataAPI.kt
endif
balt main/java/eu/bschmidt/devicepublisher/MainActivity.kt
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
let s:l = 4 - ((3 * winheight(0) + 29) / 59)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 4
normal! 027|
wincmd w
exe 'vert 1resize ' . ((&columns * 136 + 136) / 272)
exe 'vert 2resize ' . ((&columns * 135 + 136) / 272)
tabnext
edit main/res/layout/activity_main.xml
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
exe 'vert 1resize ' . ((&columns * 136 + 136) / 272)
exe 'vert 2resize ' . ((&columns * 135 + 136) / 272)
argglobal
balt main/res/layout/fragment_api_status.xml
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
let s:l = 24 - ((23 * winheight(0) + 29) / 59)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 24
normal! 05|
wincmd w
argglobal
if bufexists(fnamemodify("main/res/layout/fragment_api_status.xml", ":p")) | buffer main/res/layout/fragment_api_status.xml | else | edit main/res/layout/fragment_api_status.xml | endif
if &buftype ==# 'terminal'
  silent file main/res/layout/fragment_api_status.xml
endif
balt main/res/layout/activity_main.xml
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
let s:l = 18 - ((17 * winheight(0) + 29) / 59)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 18
normal! 09|
wincmd w
2wincmd w
exe 'vert 1resize ' . ((&columns * 136 + 136) / 272)
exe 'vert 2resize ' . ((&columns * 135 + 136) / 272)
tabnext
edit main/java/eu/bschmidt/devicepublisher/MainActivity.kt
argglobal
balt main/res/layout/fragment_resource.xml
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
let s:l = 10 - ((9 * winheight(0) + 29) / 59)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 10
normal! 044|
tabnext
edit main/java/eu/bschmidt/devicepublisher/ui/celldata/CellDataFragment.kt
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
exe 'vert 1resize ' . ((&columns * 135 + 136) / 272)
exe 'vert 2resize ' . ((&columns * 136 + 136) / 272)
argglobal
balt main/java/eu/bschmidt/devicepublisher/ui/MyCellDataFragmentRecyclerViewAdapter.kt
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
let s:l = 13 - ((12 * winheight(0) + 29) / 59)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 13
normal! 041|
wincmd w
argglobal
if bufexists(fnamemodify("main/java/eu/bschmidt/devicepublisher/ui/celldata/CellDataRecyclerViewAdapter.kt", ":p")) | buffer main/java/eu/bschmidt/devicepublisher/ui/celldata/CellDataRecyclerViewAdapter.kt | else | edit main/java/eu/bschmidt/devicepublisher/ui/celldata/CellDataRecyclerViewAdapter.kt | endif
if &buftype ==# 'terminal'
  silent file main/java/eu/bschmidt/devicepublisher/ui/celldata/CellDataRecyclerViewAdapter.kt
endif
balt main/java/eu/bschmidt/devicepublisher/ui/CellDataListFragment.kt
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
let s:l = 2 - ((1 * winheight(0) + 29) / 59)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 2
normal! 0
wincmd w
exe 'vert 1resize ' . ((&columns * 135 + 136) / 272)
exe 'vert 2resize ' . ((&columns * 136 + 136) / 272)
tabnext
edit main/java/eu/bschmidt/devicepublisher/ui/CellDataFragment.kt
argglobal
balt main/java/eu/bschmidt/devicepublisher/ui/CellFragment.kt
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
let s:l = 1 - ((0 * winheight(0) + 29) / 59)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 1
normal! 0
tabnext
edit main/java/eu/bschmidt/devicepublisher/model/celldata/CellDataViewModel.kt
argglobal
balt main/java/eu/bschmidt/devicepublisher/CellDataViewModel.kt
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
let s:l = 1 - ((0 * winheight(0) + 29) / 59)
if s:l < 1 | let s:l = 1 | endif
keepjumps exe s:l
normal! zt
keepjumps 1
normal! 0
tabnext 2
set stal=1
if exists('s:wipebuf') && len(win_findbuf(s:wipebuf)) == 0 && getbufvar(s:wipebuf, '&buftype') isnot# 'terminal'
  silent exe 'bwipe ' . s:wipebuf
endif
unlet! s:wipebuf
set winheight=1 winwidth=20
let &shortmess = s:shortmess_save
let s:sx = expand("<sfile>:p:r")."x.vim"
if filereadable(s:sx)
  exe "source " . fnameescape(s:sx)
endif
let &g:so = s:so_save | let &g:siso = s:siso_save
set hlsearch
nohlsearch
doautoall SessionLoadPost
unlet SessionLoad
" vim: set ft=vim :
