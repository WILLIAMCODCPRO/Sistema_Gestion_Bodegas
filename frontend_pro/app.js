let API='http://localhost:8080', TOKEN='', CURRENT_USER='';

async function api(path,method='GET',body=null){
  const opts={method,headers:{'Content-Type':'application/json',...(TOKEN?{Authorization:`Bearer ${TOKEN}`}:{})}};
  if(body) opts.body=JSON.stringify(body);
  const r=await fetch(API+path,opts);
  if(!r.ok){const err=await r.text().catch(()=>r.statusText);throw new Error(err||`HTTP ${r.status}`);}
  if(r.status===204) return null;
  const ct=r.headers.get('content-type')||'';
  if(ct.includes('application/json')) return r.json();
  return r.text();
}

function parseError(raw) {
  try {
    const obj = JSON.parse(raw);
    if (obj.errors && typeof obj.errors === 'object') {
      // Validation errors — list each field
      return Object.entries(obj.errors)
        .map(([field, msg]) => `• ${msg}`)
        .join('<br>');
    }
    if (obj.message) return obj.message;
  } catch(e) {}
  return raw;
}

function toast(msg, type='success') {
  const c = document.getElementById('toast-container');
  const t = document.createElement('div');
  t.className = `toast ${type}`;
  const parsed = type === 'error' ? parseError(msg.replace(/^Error: /,'')) : msg;
  t.innerHTML = `<span style="font-size:16px;flex-shrink:0">${type==='success'?'✓':'✕'}</span><span>${parsed}</span>`;
  c.appendChild(t);
  setTimeout(() => t.remove(), 5000);
}
function loading(){return`<div class="loading"><div class="spinner"></div><span>Cargando...</span></div>`;}
function empty(m='Sin registros disponibles'){return`<div class="empty"><p>${m}</p></div>`;}
function setContent(h){document.getElementById('main-content').innerHTML=h;}
function fmtDate(d){if(!d)return'—';return new Date(d).toLocaleString('es-CO',{dateStyle:'medium',timeStyle:'short'});}
function fmt(v,s='$'){if(v==null)return'—';if(typeof v==='number')return s+v.toLocaleString('es-CO',{minimumFractionDigits:0});return v;}

async function doLogin(){
  const base=document.getElementById('api-base').value.trim().replace(/\/$/,'');
  const username=document.getElementById('login-user').value.trim();
  const password=document.getElementById('login-pass').value;
  const errEl=document.getElementById('login-error');
  errEl.classList.add('hidden');
  if(!username||!password){errEl.textContent='Completa los campos requeridos.';errEl.classList.remove('hidden');return;}
  API=base;
  try{
    const data=await api('/auth/login','POST',{nombreUsuario:username,password});
    TOKEN=data.token||data.access_token||data.jwt||Object.values(data)[0];
    CURRENT_USER=username;
    document.getElementById('login-view').classList.add('hidden');
    document.getElementById('app-view').classList.remove('hidden');
    document.getElementById('user-name-display').textContent=username;
    document.getElementById('user-avatar').textContent=username[0].toUpperCase();
    navigate('dashboard');
  }catch(e){errEl.textContent=e.message;errEl.classList.remove('hidden');}
}
document.addEventListener('keydown',e=>{if(e.key==='Enter'&&!document.getElementById('login-view').classList.contains('hidden'))doLogin();});
function logout(){TOKEN='';document.getElementById('app-view').classList.add('hidden');document.getElementById('login-view').classList.remove('hidden');document.getElementById('login-pass').value='';}

const TITLES={dashboard:'Dashboard',bodegas:'Bodegas',productos:'Productos',movimientos:'Movimientos de Inventario',usuarios:'Usuarios',reportes:'Reportes',auditoria:'Registro de Auditoría'};
function navigate(view){
  document.querySelectorAll('.fnav-btn').forEach(b=>{b.classList.remove('active');if(b.getAttribute('onclick')?.includes(view))b.classList.add('active');});
  document.getElementById('page-title').textContent=TITLES[view]||view;
  views[view]?.();
}

const views={};

views.dashboard=async()=>{
  setContent(loading());
  try{
    const [B,P,M,S,TP]=await Promise.allSettled([api('/api/bodega'),api('/api/producto'),api('/api/movimientos'),api('/api/reporte/bodega/stock'),api('/api/reporte/productos/masmovidos')]);
    const b=B.value||[],p=P.value||[],m=M.value||[],s=S.value||[],tp=TP.value||[];
    const low=p.filter(x=>(x.stockProducto||0)<10);
    setContent(`
      ${low.length>0?`<div class="alert">⚠ Atención — ${low.length} producto(s) con stock bajo: ${low.map(x=>x.nombre).join(', ')}</div>`:''}
      <div class="stats-row">
        <div class="stat-card sc-lav" ><div class="s-lbl">Bodegas</div><div class="s-val">${b.length}</div><div class="s-sub">registradas</div></div>
        <div class="stat-card sc-gold"><div class="s-lbl">Productos</div><div class="s-val">${p.length}</div><div class="s-sub">en catálogo</div></div>
        <div class="stat-card sc-sage"><div class="s-lbl">Movimientos</div><div class="s-val">${m.length}</div><div class="s-sub">registrados</div></div>
        <div class="stat-card sc-blush"><div class="s-lbl">Stock Bajo</div><div class="s-val">${low.length}</div><div class="s-sub">productos</div></div>
      </div>
      <div class="two-col-wide">
        <div>
          <div class="sec-header"><span class="sec-title">Últimos movimientos</span><button class="btn btn-ghost btn-sm" onclick="navigate('movimientos')">Ver todos</button></div>
          ${m.length>0?`<div class="table-card"><table>
            <thead><tr><th>Producto</th><th>Tipo</th><th>Cantidad</th><th>Usuario</th><th>Fecha</th></tr></thead>
            <tbody>${m.slice(-8).reverse().map(x=>`<tr>
              <td><strong>${x.producto?.nombre||'—'}</strong></td>
              <td><span class="badge ${x.tipoMovimiento==='ENTRADA'?'b-sage':'b-gold'}">${x.tipoMovimiento||'—'}</span></td>
              <td>${x.cantidadProducto??'—'}</td>
              <td>${x.usuario?.nombreUsuario||'—'}</td>
              <td style="font-size:12px;color:var(--muted)">${fmtDate(x.fecha)}</td>
            </tr>`).join('')}</tbody>
          </table></div>`:empty('Sin movimientos')}
        </div>
        <div style="display:flex;flex-direction:column;gap:18px">
          <div>
            <div class="sec-header"><span class="sec-title">Stock por bodega</span></div>
            ${s.length>0?`<div class="table-card"><table>
              <thead><tr><th>Bodega</th><th>Stock total</th></tr></thead>
              <tbody>${s.map(x=>`<tr><td><strong>${x.nombreBodega||'—'}</strong></td><td><span class="badge ${(x.stockTotal||0)<50?'b-gold':'b-sage'}">${x.stockTotal??'—'} uds</span></td></tr>`).join('')}</tbody>
            </table></div>`:empty('Sin datos')}
          </div>
          <div>
            <div class="sec-header"><span class="sec-title">Más movidos</span></div>
            ${tp.length>0?`<div class="table-card"><table>
              <tbody>${tp.map((x,i)=>`<tr><td style="color:var(--muted);font-weight:800;font-size:13px">${i+1}.</td><td><strong>${x.nombreProducto||'—'}</strong></td><td><span class="badge b-lav">${x.totalMovimientos??'—'}</span></td></tr>`).join('')}</tbody>
            </table></div>`:empty('Sin datos')}
          </div>
        </div>
      </div>`);
  }catch(e){setContent(empty('Error: '+e.message));}
};

views.bodegas=async()=>{
  setContent(loading());
  try{
    const data=await api('/api/bodega');
    setContent(`<div class="sec-header"><span class="sec-title">Bodegas <span class="sec-count">${data.length}</span></span><button class="btn btn-primary" onclick="openBodegaForm()">+ Nueva bodega</button></div>
    ${data.length===0?empty():`<div class="cards-grid">${data.map(b=>`<div class="card">
      <div class="card-top"><span class="card-id">#${b.id}</span><span class="badge b-sage">Activa</span></div>
      <div class="card-name">${b.nombre}</div>
      <div class="card-info">
        <div>Ubicación &nbsp;<strong>${b.ubicacion}</strong></div>
        <div>Encargado &nbsp;<strong>${b.encargado}</strong></div>
        <div>Capacidad &nbsp;<strong>${fmt(b.capacidad,'')} unidades</strong></div>
      </div>
      <div class="card-foot">
        <button class="btn btn-ghost btn-sm" onclick="openBodegaForm(${b.id})">Editar</button>
        <button class="btn btn-danger btn-sm" onclick="deleteBodega(${b.id},'${b.nombre}')">Eliminar</button>
      </div>
    </div>`).join('')}</div>`}`);
  }catch(e){setContent(empty('Error: '+e.message));}
};

async function openBodegaForm(id=null){
  let d={};if(id){try{d=await api(`/api/bodega/${id}`);}catch(e){}}
  openModal((id?'Editar':'Nueva')+' bodega',`
    <div class="f-group"><label class="f-label">Nombre</label><input class="f-input" id="f-nombre" value="${d.nombre||''}" placeholder="Ej. Bodega Central"></div>
    <div class="f-group"><label class="f-label">Ubicación</label><input class="f-input" id="f-ubicacion" value="${d.ubicacion||''}" placeholder="Dirección"></div>
    <div class="f-row">
      <div class="f-group"><label class="f-label">Capacidad</label><input class="f-input" type="number" id="f-capacidad" value="${d.capacidad||''}" placeholder="5000"></div>
      <div class="f-group"><label class="f-label">Encargado</label><input class="f-input" id="f-encargado" value="${d.encargado||''}" placeholder="Nombre completo"></div>
    </div>
    <div style="display:flex;gap:8px;margin-top:4px">
      <button class="btn btn-primary" style="flex:1;justify-content:center" onclick="saveBodega(${id||'null'})">${id?'Guardar cambios':'Crear bodega'}</button>
      <button class="btn btn-ghost" onclick="closeModal()">Cancelar</button>
    </div>`);
}
async function saveBodega(id){
  const body={nombre:document.getElementById('f-nombre').value,ubicacion:document.getElementById('f-ubicacion').value,capacidad:parseInt(document.getElementById('f-capacidad').value),encargado:document.getElementById('f-encargado').value};
  try{if(id){await api(`/api/bodega/${id}`,'PUT',body);toast('Bodega actualizada');}else{await api('/api/bodega','POST',body);toast('Bodega creada');}closeModal();views.bodegas();}
  catch(e){toast('Error: '+e.message,'error');}
}
async function deleteBodega(id,nombre){
  if(!confirm(`¿Eliminar la bodega "${nombre}"?\n\nVerifica que no tenga productos ni movimientos asociados.`))return;
  try{await api(`/api/bodega/${id}`,'DELETE');toast('Bodega eliminada');views.bodegas();}
  catch(e){toast(e.message.includes('500')||e.message.includes('INTERNAL')?'No es posible eliminar: tiene registros asociados.':'Error: '+e.message,'error');}
}

views.productos=async()=>{
  setContent(loading());
  try{
    const [data,bodegas]=await Promise.all([api('/api/producto'),api('/api/bodega')]);
    window._bodegas=bodegas;
    const low=data.filter(x=>(x.stockProducto||0)<10);
    setContent(`<div class="sec-header"><span class="sec-title">Productos <span class="sec-count">${data.length}</span></span><div style="display:flex;gap:8px;align-items:center">${low.length>0?`<span class="badge b-blush">${low.length} bajo</span>`:''}<button class="btn btn-primary" onclick="openProductoForm()">+ Nuevo producto</button></div></div>
    ${data.length===0?empty():`<div class="cards-grid">${data.map(p=>`<div class="card">
      <div class="card-top"><span class="card-id">${p.categoria||'—'}</span><span class="badge ${(p.stockProducto||0)<10?'b-blush':'b-sage'}">${(p.stockProducto||0)<10?'Stock Bajo':'Disponible'}</span></div>
      <div class="card-name">${p.nombre}</div>
      <div class="card-info">
        <div>Precio &nbsp;<strong>$${Number(p.precio||0).toLocaleString('es-CO')}</strong></div>
        <div>Stock &nbsp;<strong style="color:${(p.stockProducto||0)<10?'var(--blush)':'var(--sage)'}">${p.stockProducto??0} unidades</strong></div>
        <div>Bodega &nbsp;<strong>${p.bodega?.nombre||'—'}</strong></div>
      </div>
      <div class="card-foot">
        <button class="btn btn-ghost btn-sm" onclick="openProductoForm(${p.id})">Editar</button>
        <button class="btn btn-danger btn-sm" onclick="deleteProducto(${p.id},'${p.nombre}')">Eliminar</button>
      </div>
    </div>`).join('')}</div>`}`);
  }catch(e){setContent(empty('Error: '+e.message));}
};

async function openProductoForm(id=null){
  let d={};if(id){try{d=await api(`/api/producto/${id}`);}catch(e){}}
  const bodegas=window._bodegas||await api('/api/bodega');
  openModal((id?'Editar':'Nuevo')+' producto',`
    <div class="f-group"><label class="f-label">Nombre</label><input class="f-input" id="f-nombre" value="${d.nombre||''}" placeholder="Nombre del producto"></div>
    <div class="f-row">
      <div class="f-group"><label class="f-label">Categoría</label><input class="f-input" id="f-categoria" value="${d.categoria||''}" placeholder="Electrónica"></div>
      <div class="f-group"><label class="f-label">Precio</label><input class="f-input" type="number" id="f-precio" value="${d.precio||''}" step="0.01"></div>
    </div>
    <div class="f-row">
      <div class="f-group"><label class="f-label">Stock</label><input class="f-input" type="number" id="f-stock" value="${d.stockProducto||''}"></div>
      <div class="f-group"><label class="f-label">Bodega</label><select class="f-input" id="f-bodega"><option value="">Seleccionar...</option>${bodegas.map(b=>`<option value="${b.id}"${d.bodega?.id===b.id?' selected':''}>${b.nombre}</option>`).join('')}</select></div>
    </div>
    <div style="display:flex;gap:8px;margin-top:4px">
      <button class="btn btn-primary" style="flex:1;justify-content:center" onclick="saveProducto(${id||'null'})">${id?'Guardar cambios':'Crear producto'}</button>
      <button class="btn btn-ghost" onclick="closeModal()">Cancelar</button>
    </div>`);
}
async function saveProducto(id){
  const body={nombre:document.getElementById('f-nombre').value,categoria:document.getElementById('f-categoria').value,precio:parseFloat(document.getElementById('f-precio').value),stockProducto:parseInt(document.getElementById('f-stock').value),bodegaId:parseInt(document.getElementById('f-bodega').value)||null};
  try{if(id){await api(`/api/producto/${id}`,'PUT',body);toast('Producto actualizado');}else{await api('/api/producto','POST',body);toast('Producto creado');}closeModal();views.productos();}
  catch(e){toast('Error: '+e.message,'error');}
}
async function deleteProducto(id,nombre){
  if(!confirm(`¿Eliminar el producto "${nombre}"?`))return;
  try{await api(`/api/producto/${id}`,'DELETE');toast('Producto eliminado');views.productos();}
  catch(e){toast(e.message.includes('500')||e.message.includes('INTERNAL')?'No es posible eliminar: tiene movimientos asociados.':'Error: '+e.message,'error');}
}

function renderMovimientos(data) {
  return data.length===0 ? empty('Sin movimientos') :
    `<div class="cards-grid">${data.slice().reverse().map(m=>`<div class="card">
      <div class="card-top"><span class="card-id">MOV-${String(m.id).padStart(4,'0')}</span><span class="badge ${m.tipoMovimiento==='ENTRADA'?'b-sage':m.tipoMovimiento==='SALIDA'?'b-gold':'b-lav'}">${m.tipoMovimiento||'—'}</span></div>
      <div class="card-name">${m.producto?.nombre||'—'}</div>
      <div class="card-info">
        <div>Fecha &nbsp;<strong>${fmtDate(m.fecha)}</strong></div>
        <div>Usuario &nbsp;<strong>${m.usuario?.nombreUsuario||'—'}</strong></div>
        <div>Cantidad &nbsp;<strong>${m.cantidadProducto??'—'} unidades</strong></div>
        ${m.bodegaOrigen?`<div>Origen &nbsp;<strong>${m.bodegaOrigen.nombre}</strong></div>`:''}
        ${m.bodegaDestino?`<div>Destino &nbsp;<strong>${m.bodegaDestino.nombre}</strong></div>`:''}
      </div>
    </div>`).join('')}</div>`;
}

async function filtrarMovimientos() {
  const f1 = document.getElementById('fecha-desde').value;
  const f2 = document.getElementById('fecha-hasta').value;
  if (!f1 || !f2) { toast('Selecciona ambas fechas', 'error'); return; }
  const desde = f1 + 'T00:00:00';
  const hasta  = f2 + 'T23:59:59';
  const area = document.getElementById('mov-results');
  area.innerHTML = loading();
  try {
    const data = await api(`/api/movimientos/${encodeURIComponent(desde)}/${encodeURIComponent(hasta)}`);
    area.innerHTML = `<div style="margin-bottom:12px"><span class="sec-title" style="font-size:15px">Resultados: <span class="sec-count">${data.length}</span></span></div>` + renderMovimientos(data);
  } catch(e) { area.innerHTML = empty('Error: ' + e.message); }
}

async function limpiarFiltroMov() {
  document.getElementById('fecha-desde').value = '';
  document.getElementById('fecha-hasta').value = '';
  const area = document.getElementById('mov-results');
  area.innerHTML = loading();
  try {
    const data = await api('/api/movimientos');
    area.innerHTML = renderMovimientos(data);
  } catch(e) { area.innerHTML = empty('Error: ' + e.message); }
}

views.movimientos=async()=>{
  setContent(loading());
  try{
    const data=await api('/api/movimientos');
    setContent(`
      <div class="sec-header">
        <span class="sec-title">Movimientos <span class="sec-count">${data.length}</span></span>
        <button class="btn btn-primary" onclick="openMovimientoForm()">+ Registrar</button>
      </div>
      <div style="background:var(--ivory);border:var(--outline);border-radius:var(--radius-big);padding:16px 20px;margin-bottom:20px;box-shadow:var(--shadow-comic-sm);display:flex;align-items:flex-end;gap:12px;flex-wrap:wrap;">
        <div>
          <div class="f-label" style="margin-bottom:5px">Desde</div>
          <input type="date" id="fecha-desde" class="f-input" style="width:160px;box-shadow:none">
        </div>
        <div>
          <div class="f-label" style="margin-bottom:5px">Hasta</div>
          <input type="date" id="fecha-hasta" class="f-input" style="width:160px;box-shadow:none">
        </div>
        <button class="btn btn-primary" onclick="filtrarMovimientos()">Filtrar</button>
        <button class="btn btn-ghost" onclick="limpiarFiltroMov()">Ver todos</button>
      </div>
      <div id="mov-results">${renderMovimientos(data)}</div>
    `);
  }catch(e){setContent(empty('Error: '+e.message));}
};

async function openMovimientoForm(){
  const [productos,bodegas]=await Promise.all([api('/api/producto'),api('/api/bodega')]).catch(()=>[[],[]]);
  openModal('Registrar movimiento',`
    <div class="f-group"><label class="f-label">Tipo</label><select class="f-input" id="f-tipo"><option value="ENTRADA">ENTRADA</option><option value="SALIDA">SALIDA</option><option value="TRASNFERENCIA">TRANSFERENCIA</option></select></div>
    <div class="f-row">
      <div class="f-group"><label class="f-label">Producto</label><select class="f-input" id="f-producto"><option value="">Seleccionar...</option>${productos.map(p=>`<option value="${p.id}">${p.nombre}</option>`).join('')}</select></div>
      <div class="f-group"><label class="f-label">Cantidad</label><input class="f-input" type="number" id="f-cantidad" placeholder="0" min="1"></div>
    </div>
    <div class="f-row">
      <div class="f-group"><label class="f-label">Bodega origen</label><select class="f-input" id="f-origen"><option value="">Ninguna</option>${bodegas.map(b=>`<option value="${b.id}">${b.nombre}</option>`).join('')}</select></div>
      <div class="f-group"><label class="f-label">Bodega destino</label><select class="f-input" id="f-destino"><option value="">Ninguna</option>${bodegas.map(b=>`<option value="${b.id}">${b.nombre}</option>`).join('')}</select></div>
    </div>
    <div style="display:flex;gap:8px;margin-top:4px">
      <button class="btn btn-primary" style="flex:1;justify-content:center" onclick="saveMovimiento()">Registrar movimiento</button>
      <button class="btn btn-ghost" onclick="closeModal()">Cancelar</button>
    </div>`);
}
async function saveMovimiento(){
  const body={tipoMovimiento:document.getElementById('f-tipo').value,productoID:parseInt(document.getElementById('f-producto').value)||null,cantidadProducto:parseInt(document.getElementById('f-cantidad').value),bodegaOrigenID:parseInt(document.getElementById('f-origen').value)||null,bodegaDestinoID:parseInt(document.getElementById('f-destino').value)||null};
  try{await api('/api/movimientos','POST',body);toast('Movimiento registrado');closeModal();views.movimientos();}
  catch(e){toast('Error: '+e.message,'error');}
}

views.usuarios=async()=>{
  setContent(loading());
  try{
    const data=await api('/api/usuario');
    setContent(`<div class="sec-header"><span class="sec-title">Usuarios <span class="sec-count">${data.length}</span></span><button class="btn btn-primary" onclick="openRegistroUsuario()">+ Nuevo usuario</button></div>
    ${data.length===0?empty():`<div class="cards-grid">${data.map(u=>`<div class="card">
      <div class="card-top"><span class="card-id">#${u.id}</span><span class="badge ${u.rol==='ADMIN'?'b-violet':'b-lav'}">${u.rol==='ADMIN'?'Admin':'Empleado'}</span></div>
      <div style="display:flex;align-items:center;gap:12px;margin-bottom:10px">
        <div class="avatar" style="width:46px;height:46px;font-size:19px;border:3px solid var(--ink)">${(u.nombreUsuario||'?')[0].toUpperCase()}</div>
        <div>
          <div style="font-family:var(--FD);font-size:18px;font-weight:700;color:var(--ink)">${u.nombreUsuario||'—'}</div>
          <div style="font-family:var(--FD);font-size:12px;color:var(--muted)">${u.rol==='ADMIN'?'Administrador':'Empleado'}</div>
        </div>
      </div>
    </div>`).join('')}</div>`}`);
  }catch(e){setContent(empty('Error: '+e.message));}
};

function openRegistroUsuario(){
  openModal('Nuevo usuario',`
    <div class="f-group"><label class="f-label">Nombre de usuario</label><input class="f-input" id="f-username" placeholder="usuario123"></div>
    <div class="f-group"><label class="f-label">Contraseña</label><input class="f-input" type="password" id="f-password" placeholder="••••••••"></div>
    <div class="f-group"><label class="f-label">Rol</label><select class="f-input" id="f-rol"><option value="EMPLEADO">Empleado</option><option value="ADMIN">Administrador</option></select></div>
    <div style="display:flex;gap:8px;margin-top:4px">
      <button class="btn btn-primary" style="flex:1;justify-content:center" onclick="saveUsuario()">Crear usuario</button>
      <button class="btn btn-ghost" onclick="closeModal()">Cancelar</button>
    </div>`);
}
async function saveUsuario(){
  const body={nombreUsuario:document.getElementById('f-username').value,password:document.getElementById('f-password').value,rol:document.getElementById('f-rol').value};
  try{await api('/auth/register','POST',body);toast('Usuario creado');closeModal();views.usuarios();}
  catch(e){toast('Error: '+e.message,'error');}
}

views.reportes=async()=>{
  setContent(loading());
  try{
    const [stock,topProd]=await Promise.all([api('/api/reporte/bodega/stock'),api('/api/reporte/productos/masmovidos')]);
    setContent(`<div class="two-col">
      <div>
        <div class="sec-header"><span class="sec-title">Productos más movidos</span></div>
        ${topProd.length>0?`<div class="table-card"><table>
          <thead><tr><th>#</th><th>Producto</th><th>Movimientos</th></tr></thead>
          <tbody>${topProd.map((x,i)=>`<tr><td style="font-weight:800;color:var(--muted)">${i+1}</td><td><strong>${x.nombreProducto||'—'}</strong></td><td><span class="badge b-lav">${x.totalMovimientos??'—'}</span></td></tr>`).join('')}</tbody>
        </table></div>`:empty('Sin datos')}
      </div>
      <div>
        <div class="sec-header"><span class="sec-title">Stock por bodega</span></div>
        ${stock.length>0?`<div class="table-card"><table>
          <thead><tr><th>Bodega</th><th>Stock total</th></tr></thead>
          <tbody>${stock.map(x=>`<tr><td><strong>${x.nombreBodega||'—'}</strong></td><td><span class="badge ${(x.stockTotal||0)<50?'b-gold':'b-sage'}">${x.stockTotal??'—'} uds</span></td></tr>`).join('')}</tbody>
        </table></div>`:empty('Sin datos')}
      </div>
    </div>`);
  }catch(e){setContent(empty('Error: '+e.message));}
};

function fmtJson(str){
  if(!str)return`<span style="color:var(--muted)">—</span>`;
  try{const o=typeof str==='string'?JSON.parse(str):str;return Object.entries(o).map(([k,v])=>`<span style="color:var(--muted);font-size:11px">${k}:</span> <strong>${v??'—'}</strong>`).join('<br>');}
  catch{return`<span style="font-size:12px;color:var(--muted)">${str}</span>`;}
}

function renderAuditoria(data) {
  if (data.length===0) return empty('Sin registros');
  return `<div class="table-card"><table>
    <thead><tr><th>ID</th><th>Fecha</th><th>Entidad</th><th>Operación</th><th>Usuario</th><th>Antes</th><th>Después</th></tr></thead>
    <tbody>${data.slice().reverse().map(a=>`<tr>
      <td style="font-family:monospace;color:var(--muted);font-size:12px">${a.id}</td>
      <td style="white-space:nowrap;font-size:12px;color:var(--muted)">${fmtDate(a.fecha)}</td>
      <td><strong>${a.entidad||'—'}</strong></td>
      <td><span class="badge ${a.tipoOperacion==='INSERT'?'b-sage':a.tipoOperacion==='DELETE'?'b-blush':'b-gold'}">${a.tipoOperacion||'—'}</span></td>
      <td><div style="display:flex;align-items:center;gap:8px">
        <div class="avatar" style="width:28px;height:28px;font-size:11px;border:2px solid var(--ink)">${(a.usuario?.nombreUsuario||'?')[0].toUpperCase()}</div>
        <div><div style="font-family:var(--FD);font-weight:700;font-size:14px">${a.usuario?.nombreUsuario||'—'}</div>
        <span class="badge b-violet" style="font-size:10px">${a.usuario?.rol||'—'}</span></div>
      </div></td>
      <td style="font-size:12px;line-height:1.8;max-width:150px">${fmtJson(a.valorAntiguo)}</td>
      <td style="font-size:12px;line-height:1.8;max-width:150px">${fmtJson(a.valorNuevo)}</td>
    </tr>`).join('')}</tbody>
  </table></div>`;
}

async function filtrarAuditoria() {
  const sel = document.getElementById('audit-usuario-select');
  const id = sel.value;
  if (!id) { await cargarTodasAuditorias(); return; }
  const area = document.getElementById('audit-results');
  area.innerHTML = loading();
  try {
    const data = await api(`/api/auditoria/usuario/${id}`);
    const nombre = sel.options[sel.selectedIndex].text;
    area.innerHTML = `<div style="margin-bottom:10px"><span class="sec-title" style="font-size:15px">Auditorías de <strong>${nombre}</strong>: <span class="sec-count">${data.length}</span></span></div>` + renderAuditoria(data);
  } catch(e) { area.innerHTML = empty('Error: ' + e.message); }
}

async function cargarTodasAuditorias() {
  const area = document.getElementById('audit-results');
  if (area) {
    area.innerHTML = loading();
    try {
      const data = await api('/api/auditoria');
      area.innerHTML = renderAuditoria(data);
    } catch(e) { area.innerHTML = empty('Error: ' + e.message); }
  }
}

views.auditoria=async()=>{
  setContent(loading());
  try{
    const [data, usuarios] = await Promise.all([api('/api/auditoria'), api('/api/usuario')]);
    setContent(`
      <div class="sec-header">
        <span class="sec-title">Auditoría <span class="sec-count">${data.length}</span></span>
      </div>

      <!-- filtro por usuario -->
      <div style="background:var(--ivory);border:var(--outline);border-radius:var(--radius-big);padding:16px 20px;margin-bottom:20px;box-shadow:var(--shadow-comic-sm);display:flex;align-items:flex-end;gap:12px;flex-wrap:wrap;">
        <div style="flex:1;min-width:200px">
          <div class="f-label" style="margin-bottom:5px">Filtrar por usuario</div>
          <select id="audit-usuario-select" class="f-input" style="box-shadow:none" onchange="filtrarAuditoria()">
            <option value="">Todos los usuarios</option>
            ${usuarios.map(u=>`<option value="${u.id}">${u.nombreUsuario} (${u.rol})</option>`).join('')}
          </select>
        </div>
        <button class="btn btn-ghost" onclick="document.getElementById('audit-usuario-select').value='';cargarTodasAuditorias()">Limpiar filtro</button>
      </div>

      <div id="audit-results">${renderAuditoria(data)}</div>
    `);
  }catch(e){setContent(empty('Error: '+e.message));}
};

function openModal(title,html){document.getElementById('modal-title-text').textContent=title;document.getElementById('modal-body').innerHTML=html;document.getElementById('modal-overlay').classList.add('open');}
function closeModal(){document.getElementById('modal-overlay').classList.remove('open');}
function closeModalOnBg(e){if(e.target===document.getElementById('modal-overlay'))closeModal();}
