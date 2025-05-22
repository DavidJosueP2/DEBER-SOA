const API_BASE = '/api/students'; // Ajusta aquí si tu endpoint fuera diferente
let url;
let selectedRow = null;

document.addEventListener('DOMContentLoaded', () => {
    loadData();
});

function showToast(message, type = 'primary') {
    const toastEl = $('#toastMsg');
    $('#toastBody').text(message);
    toastEl.removeClass().addClass(`toast align-items-center text-white bg-${type} border-0`);
    new bootstrap.Toast(toastEl[0]).show();
}

function showConfirm(message, onConfirm) {
    $('#confirmMessage').text(message);
    const confirmBtn = $('#confirmBtn');
    const confirmModal = new bootstrap.Modal(document.getElementById('confirmModal'));

    confirmBtn.off('click').on('click', () => {
        confirmModal.hide();
        onConfirm();
    });

    confirmModal.show();
}

function loadData() {
    $.ajax({
        url: API_BASE,
        type: "GET",
        dataType: "json",
        success: function(data) {
            const tbody = $('#userTableBody');
            tbody.empty();
            data.forEach(user => {
                const row = $('<tr></tr>').click(() => selectRow(row, user));
                row.append(`<td>${user.id}</td>`);       // Ahora mostramos el id
                row.append(`<td>${user.cedula}</td>`);  
                row.append(`<td>${user.name}</td>`);
                row.append(`<td>${user.lastname}</td>`);
                row.append(`<td>${user.address}</td>`);
                row.append(`<td>${user.telephone}</td>`);
                tbody.append(row);
            });
        },
        error: function(xhr) {
            showToast('Failed to load data: ' + xhr.responseText, 'danger');
        }
    });
}

function selectRow(rowElement, rowData) {
    $('#userTable tbody tr').removeClass('table-active');
    $(rowElement).addClass('table-active');
    selectedRow = rowData;
}

function newUser() {
    $('#fm')[0].reset();
    selectedRow = null;
    url = API_BASE;
    $('#modalTitle').text('New User');
    new bootstrap.Modal(document.getElementById('userModal')).show();
}

function editUser() {
    if (!selectedRow) {
        showToast('Please select a user first.', 'warning');
        return;
    }
    $('#fm')[0].reset();
    for (let key in selectedRow) {
        if (key !== 'id') {
            $(`[name="${key}"]`).val(selectedRow[key]);
        }
    }
    url = `${API_BASE}/id/${selectedRow.id}`;
    $('#modalTitle').text('Edit User');
    new bootstrap.Modal(document.getElementById('userModal')).show();
}

function saveUser() {
    const formData = $('#fm').serializeArray();
    const dataObj = {};
    formData.forEach(item => {
        dataObj[item.name] = item.value;
    });

    const isEdit = selectedRow !== null;
    const method = isEdit ? 'PUT' : 'POST';

    $.ajax({
        url: url,
        type: method,
        data: JSON.stringify(dataObj),
        contentType: 'application/json',
        success: function() {
            bootstrap.Modal.getInstance(document.getElementById('userModal')).hide();
            loadData();
            showToast(isEdit ? 'User updated successfully.' : 'User created successfully.', 'success');
        },
        error: function(xhr) {
            showToast('Failed to save user: ' + xhr.responseText, 'danger');
        }
    });
}

function proxyDestroyUser() {
    if (!selectedRow) {
        showToast('Please select a user first.', 'warning');
        return;
    }
    showConfirm('Are you sure you want to delete this user?', () => {
        destroyUser(selectedRow.id);
    });
}

function destroyUser(id) {
    $.ajax({
        url: `${API_BASE}/id/${encodeURIComponent(id)}`, // DELETE to /api/students/id/{id}
        type: 'DELETE',
        success: function() {
            loadData();
            showToast('User deleted successfully.', 'success');
        },
        error: function(xhr) {
            showToast('Failed to delete user: ' + xhr.responseText, 'danger');
        }
    });
}
