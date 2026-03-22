<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>PetCare — Nueva Cita</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style> body { font-family: 'DM Sans', sans-serif; } </style>
</head>
<body class="min-h-screen bg-zinc-50">
<%@ include file="../components/navbar.jsp" %>
<main class="max-w-2xl mx-auto px-6 pt-24 pb-12">
    <div class="mb-8">
        <a href="${pageContext.request.contextPath}/citas" class="text-sm text-zinc-400 hover:text-zinc-600 transition-colors">← Volver</a>
        <h1 class="text-2xl font-semibold text-zinc-900 tracking-tight mt-3">Nueva cita</h1>
    </div>
    <% if (request.getAttribute("error") != null) { %>
    <div class="mb-4 px-4 py-3 bg-red-50 border border-red-100 rounded-xl text-sm text-red-600"><%= request.getAttribute("error") %></div>
    <% } %>
    <div class="bg-white border border-zinc-100 rounded-2xl shadow-sm p-8">
        <form action="${pageContext.request.contextPath}/citas" method="post" class="space-y-5">
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Mascota *</label>
                <select name="idMascota" required class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 transition-all">
                    <option value="">Selecciona una mascota...</option>
                    <c:forEach var="mascota" items="${mascotas}">
                        <option value="${mascota.idMascota}">${mascota.nombre} — ${mascota.nombreDueno}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Veterinario *</label>
                <select name="idVeterinario" required class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 transition-all">
                    <option value="">Selecciona un veterinario...</option>
                    <c:forEach var="vet" items="${veterinarios}">
                        <option value="${vet.idVeterinario}">Dr. ${vet.nombre} — ${vet.especialidad}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Fecha y Hora *</label>
                <input type="datetime-local" name="fechaHora" required
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 transition-all"/>
            </div>
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Motivo *</label>
                <select name="motivo" required class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 transition-all">
                    <option value="Consulta general">Consulta general</option>
                    <option value="Vacunación">Vacunación</option>
                    <option value="Cirugía">Cirugía</option>
                    <option value="Control">Control</option>
                    <option value="Urgencia">Urgencia</option>
                </select>
            </div>
            <div class="flex gap-3 pt-2">
                <button type="submit" class="flex-1 py-2.5 bg-zinc-900 text-white text-sm font-medium rounded-xl hover:bg-zinc-700 active:scale-95 transition-all">Registrar cita</button>
                <a href="${pageContext.request.contextPath}/citas" class="flex-1 py-2.5 text-center text-sm text-zinc-500 border border-zinc-200 rounded-xl hover:bg-zinc-50 transition-all">Cancelar</a>
            </div>
        </form>
    </div>
</main>
</body>
</html>