<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>PetCare — Nueva Mascota</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style> body { font-family: 'DM Sans', sans-serif; } </style>
</head>
<body class="min-h-screen bg-zinc-50">
<%@ include file="../components/navbar.jsp" %>
<main class="max-w-2xl mx-auto px-6 pt-24 pb-12">
    <div class="mb-8">
        <a href="${pageContext.request.contextPath}/mascotas" class="text-sm text-zinc-400 hover:text-zinc-600 transition-colors">← Volver</a>
        <h1 class="text-2xl font-semibold text-zinc-900 tracking-tight mt-3">Nueva mascota</h1>
    </div>
    <% if (request.getAttribute("error") != null) { %>
    <div class="mb-4 px-4 py-3 bg-red-50 border border-red-100 rounded-xl text-sm text-red-600"><%= request.getAttribute("error") %></div>
    <% } %>
    <div class="bg-white border border-zinc-100 rounded-2xl shadow-sm p-8">
        <form action="${pageContext.request.contextPath}/mascotas" method="post" class="space-y-5">
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Nombre *</label>
                <input type="text" name="nombre" required value="${mascota.nombre}" placeholder="Ej: Firulais"
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 placeholder:text-zinc-300 transition-all"/>
            </div>
            <div class="grid grid-cols-2 gap-4">
                <div>
                    <label class="block text-sm font-medium text-zinc-600 mb-1.5">Especie</label>
                    <select name="especie" class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 transition-all">
                        <option value="Perro">🐶 Perro</option>
                        <option value="Gato">🐱 Gato</option>
                        <option value="Ave">🦜 Ave</option>
                        <option value="Conejo">🐰 Conejo</option>
                        <option value="Otro">Otro</option>
                    </select>
                </div>
                <div>
                    <label class="block text-sm font-medium text-zinc-600 mb-1.5">Edad (años)</label>
                    <input type="number" name="edad" min="0" max="30" value="${mascota.edad}" placeholder="Ej: 3"
                           class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 placeholder:text-zinc-300 transition-all"/>
                </div>
            </div>
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Raza</label>
                <input type="text" name="raza" value="${mascota.raza}" placeholder="Ej: Labrador"
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 placeholder:text-zinc-300 transition-all"/>
            </div>
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Cédula del dueño *</label>
                <input type="text" name="cedulaDueno" required value="${mascota.cedulaDueno}" placeholder="Ej: 1097032932"
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 placeholder:text-zinc-300 transition-all"/>
            </div>
            <div class="flex gap-3 pt-2">
                <button type="submit" class="flex-1 py-2.5 bg-zinc-900 text-white text-sm font-medium rounded-xl hover:bg-zinc-700 active:scale-95 transition-all">Registrar mascota</button>
                <a href="${pageContext.request.contextPath}/mascotas" class="flex-1 py-2.5 text-center text-sm text-zinc-500 border border-zinc-200 rounded-xl hover:bg-zinc-50 transition-all">Cancelar</a>
            </div>
        </form>
    </div>
</main>
</body>
</html>