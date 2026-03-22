<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>PetCare — Historia Clínica</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style> body { font-family: 'DM Sans', sans-serif; } </style>
</head>
<body class="min-h-screen bg-zinc-50">
<%@ include file="../components/navbar.jsp" %>
<main class="max-w-4xl mx-auto px-6 pt-24 pb-12">
    <div class="flex items-center justify-between mb-8">
        <div>
            <a href="${pageContext.request.contextPath}/mascotas"
               class="text-sm text-zinc-400 hover:text-zinc-600 transition-colors">← Volver a mascotas</a>
            <h1 class="text-2xl font-semibold text-zinc-900 tracking-tight mt-3">Historia Clínica</h1>
            <c:if test="${not empty historias}">
                <p class="text-sm text-zinc-400 mt-1">${historias[0].nombreMascota} — ${historias[0].especie}</p>
            </c:if>
        </div>

        <%-- ✅ Botones juntos --%>
        <div class="flex gap-3">
            <a href="${pageContext.request.contextPath}/pdf/historia?idMascota=${idMascota}"
               class="px-4 py-2 bg-zinc-100 text-zinc-600 text-sm font-medium rounded-xl
                      hover:bg-zinc-200 transition-all">
                ⬇ Descargar PDF
            </a>
            <a href="${pageContext.request.contextPath}/historias?accion=nueva&idMascota=${idMascota}"
               class="px-4 py-2 bg-zinc-900 text-white text-sm font-medium rounded-xl
                      hover:bg-zinc-700 transition-all">
                + Nueva entrada
            </a>
        </div>
    </div>

    <!-- Timeline de entradas -->
    <div class="space-y-4">
        <c:forEach var="historia" items="${historias}">
            <div class="bg-white border border-zinc-100 rounded-2xl p-6 shadow-sm">
                <div class="flex items-start justify-between">
                    <div class="flex-1">
                        <div class="flex items-center gap-3 mb-3">
                            <span class="text-xs font-mono text-zinc-400">${historia.fecha}</span>
                            <span class="w-1.5 h-1.5 bg-zinc-300 rounded-full"></span>
                            <span class="text-xs text-zinc-400">${historia.especie}</span>
                        </div>
                        <div class="mb-3">
                            <p class="text-xs font-medium text-zinc-400 uppercase tracking-wider mb-1">Diagnóstico</p>
                            <p class="text-sm text-zinc-700">${historia.descripcion}</p>
                        </div>
                        <c:if test="${not empty historia.medicacion}">
                            <div>
                                <p class="text-xs font-medium text-zinc-400 uppercase tracking-wider mb-1">Medicación</p>
                                <p class="text-sm text-zinc-500">${historia.medicacion}</p>
                            </div>
                        </c:if>
                    </div>
                    <a href="${pageContext.request.contextPath}/historias?accion=eliminar&id=${historia.idHistorial}&idMascota=${idMascota}"
                       onclick="return confirm('¿Eliminar esta entrada?')"
                       class="ml-4 px-3 py-1.5 text-xs text-red-400 hover:text-red-600 hover:bg-red-50 rounded-lg transition-all">
                        Eliminar
                    </a>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty historias}">
            <div class="bg-white border border-zinc-100 rounded-2xl p-12 text-center shadow-sm">
                <p class="text-zinc-300 text-sm">No hay entradas en la historia clínica</p>
            </div>
        </c:if>
    </div>
</main>
</body>
</html>