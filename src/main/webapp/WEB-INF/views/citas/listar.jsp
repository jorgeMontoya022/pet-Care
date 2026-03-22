<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>PetCare — Citas</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style> body { font-family: 'DM Sans', sans-serif; } </style>
</head>
<body class="min-h-screen bg-zinc-50">
<%@ include file="../components/navbar.jsp" %>
<main class="max-w-7xl mx-auto px-6 pt-24 pb-12">
    <div class="flex items-center justify-between mb-8">
        <div>
            <h1 class="text-2xl font-semibold text-zinc-900 tracking-tight">Citas</h1>
            <p class="text-sm text-zinc-400 mt-1">Agenda de consultas</p>
        </div>
        <a href="${pageContext.request.contextPath}/citas?accion=nueva"
           class="px-4 py-2 bg-zinc-900 text-white text-sm font-medium rounded-xl hover:bg-zinc-700 transition-all">
            + Nueva cita
        </a>
    </div>

    <div class="bg-white border border-zinc-100 rounded-2xl overflow-hidden shadow-sm">
        <table class="w-full text-sm">
            <thead>
            <tr class="border-b border-zinc-100 bg-zinc-50">
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Fecha y Hora</th>
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Mascota</th>
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Veterinario</th>
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Motivo</th>
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Estado</th>
                <th class="text-left px-6 py-4 text-xs font-medium text-zinc-400 uppercase tracking-wider">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cita" items="${citas}">
                <tr class="border-b border-zinc-50 hover:bg-zinc-50 transition-colors">
                    <td class="px-6 py-4 text-zinc-500 text-xs font-mono">${cita.fechaHora}</td>
                    <td class="px-6 py-4 text-zinc-900 font-medium">${cita.nombreMascota}</td>
                    <td class="px-6 py-4 text-zinc-500">${cita.nombreVeterinario}</td>
                    <td class="px-6 py-4 text-zinc-500">${cita.motivo}</td>
                    <td class="px-6 py-4">
                        <c:choose>
                            <c:when test="${cita.estado == 'PROGRAMADA'}">
                                <span class="px-2.5 py-1 bg-blue-50 text-blue-600 text-xs rounded-lg">Programada</span>
                            </c:when>
                            <c:when test="${cita.estado == 'COMPLETADA'}">
                                <span class="px-2.5 py-1 bg-green-50 text-green-600 text-xs rounded-lg">Completada</span>
                            </c:when>
                            <c:otherwise>
                                <span class="px-2.5 py-1 bg-red-50 text-red-500 text-xs rounded-lg">Cancelada</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="px-6 py-4">
                        <div class="flex items-center gap-2">
                            <a href="${pageContext.request.contextPath}/citas?accion=cambiarEstado&id=${cita.idCita}&estado=COMPLETADA"
                               class="px-3 py-1.5 text-xs text-green-600 bg-green-50 rounded-lg hover:bg-green-100 transition-all">✓ Completar</a>
                            <a href="${pageContext.request.contextPath}/citas?accion=cambiarEstado&id=${cita.idCita}&estado=CANCELADA"
                               class="px-3 py-1.5 text-xs text-zinc-500 bg-zinc-100 rounded-lg hover:bg-zinc-200 transition-all">Cancelar</a>
                            <a href="${pageContext.request.contextPath}/citas?accion=eliminar&id=${cita.idCita}"
                               onclick="return confirm('¿Eliminar esta cita?')"
                               class="px-3 py-1.5 text-xs text-red-500 bg-red-50 rounded-lg hover:bg-red-100 transition-all">Eliminar</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty citas}">
                <tr><td colspan="6" class="px-6 py-12 text-center text-zinc-300 text-sm">No hay citas registradas</td></tr>
            </c:if>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>