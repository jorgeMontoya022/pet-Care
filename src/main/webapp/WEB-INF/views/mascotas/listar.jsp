<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>PetCare — Mascotas</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style> body { font-family: 'DM Sans', sans-serif; } </style>
</head>
<body class="min-h-screen bg-zinc-50">
<%@ include file="../components/navbar.jsp" %>
<main class="max-w-7xl mx-auto px-6 pt-24 pb-12">
    <div class="flex items-center justify-between mb-8">
        <div>
            <h1 class="text-2xl font-semibold text-zinc-900 tracking-tight">Mascotas</h1>
            <p class="text-sm text-zinc-400 mt-1">Pacientes registrados</p>
        </div>
        <a href="${pageContext.request.contextPath}/mascotas?accion=nuevo"
           class="px-4 py-2 bg-zinc-900 text-white text-sm font-medium rounded-xl hover:bg-zinc-700 transition-all">
            + Nueva mascota
        </a>
    </div>

    <c:if test="${not empty error}">
        <div class="mb-4 px-4 py-3 bg-red-50 border border-red-100 rounded-xl text-sm text-red-600">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/mascotas" method="get" class="mb-6 flex gap-3">
        <input type="hidden" name="accion" value="buscarPorDueno"/>
        <input type="text" name="cedula" placeholder="Buscar por cédula del dueño..."
               value="${cedulaBuscada}"
               class="flex-1 px-4 py-2.5 text-sm bg-white border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 placeholder:text-zinc-300 transition-all"/>
        <button type="submit" class="px-4 py-2.5 bg-zinc-100 text-zinc-600 text-sm rounded-xl hover:bg-zinc-200 transition-all">Buscar</button>
        <a href="${pageContext.request.contextPath}/mascotas" class="px-4 py-2.5 text-zinc-400 text-sm rounded-xl hover:bg-zinc-100 transition-all">Limpiar</a>
    </form>

    <div class="bg-white border border-zinc-100 rounded-2xl overflow-hidden shadow-sm">
        <table class="w-full text-sm">
            <thead>
            <tr class="border-b border-zinc-100 bg-zinc-50">
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Nombre</th>
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Especie</th>
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Raza</th>
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Edad</th>
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Dueño</th>
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="mascota" items="${mascotas}">
                <tr class="border-b border-zinc-50 hover:bg-zinc-50 transition-colors">
                    <td class="px-6 py-4 text-zinc-900 font-medium">${mascota.nombre}</td>
                    <td class="px-6 py-4">
                        <span class="px-2.5 py-1 bg-zinc-100 text-zinc-600 text-xs rounded-lg">${mascota.especie}</span>
                    </td>
                    <td class="px-6 py-4 text-zinc-500">${mascota.raza}</td>
                    <td class="px-6 py-4 text-zinc-500">${mascota.edad} años</td>
                    <td class="px-6 py-4 text-zinc-500">${mascota.nombreDueno}</td>
                    <td class="px-6 py-4">
                        <div class="flex items-center gap-2">
                            <a href="${pageContext.request.contextPath}/historias?idMascota=${mascota.idMascota}"
                               class="px-3 py-1.5 text-xs text-blue-600 bg-blue-50 rounded-lg hover:bg-blue-100 transition-all">Historial</a>
                            <a href="${pageContext.request.contextPath}/mascotas?accion=editar&id=${mascota.idMascota}"
                               class="px-3 py-1.5 text-xs text-zinc-600 bg-zinc-100 rounded-lg hover:bg-zinc-200 transition-all">Editar</a>
                            <a href="${pageContext.request.contextPath}/mascotas?accion=eliminar&id=${mascota.idMascota}"
                               onclick="return confirm('¿Eliminar a ${mascota.nombre}?')"
                               class="px-3 py-1.5 text-xs text-red-500 bg-red-50 rounded-lg hover:bg-red-100 transition-all">Eliminar</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty mascotas}">
                <tr>
                    <td colspan="6" class="px-6 py-12 text-center text-zinc-300 text-sm">No hay mascotas registradas</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>