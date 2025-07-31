// src/api/tierlistApi.js

const API_BASE_URL = 'http://localhost:8080'; // Modifica con l'URL del tuo backend

// Funzione generica per le chiamate API
async function fetchApi(endpoint, method = 'GET', body = null, token = null) {
    const headers = {
        'Content-Type': 'application/json',
    };

    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    const config = {
        method,
        headers,
    };

    if (body) {
        config.body = JSON.stringify(body);
    }

    const response = await fetch(`${API_BASE_URL}${endpoint}`, config);

    if (!response.ok) {
        // Prima prova a parsare come JSON, se fallisce restituisci il testo
        const errorText = await response.text();
        try {
            const errorJson = JSON.parse(errorText);
            throw new Error(errorJson.message || 'Errore nella richiesta API');
        } catch {
            throw new Error(errorText || 'Errore nella richiesta API');
        }
    }

    if (response.status === 204) {
        return null;
    }

    return response.json();
}

// Auth API
export const authApi = {
    login: async (username, password) => {
        return fetchApi('/auth/login', 'POST', { username, password });
    },

    register: async (username, password, ruolo) => {
        return fetchApi('/auth/register', 'POST', { username, password, ruolo });
    },

    refreshToken: async (refreshToken) => {
        return fetchApi('/auth/refresh', 'POST', { refreshToken });
    },

    logout: async (refreshToken) => {
        return fetchApi('/auth/logout', 'POST', { refreshToken });
    },
};

// Public API
export const publicApi = {
    getPublicHello: async () => {
        return fetchApi('/public/hello');
    },
};

// User API
export const userApi = {
    getUserInfo: async (token) => {
        return fetchApi('/user/info', 'GET', null, token);
    },

    updateUser: async (id, userData, token) => {
        return fetchApi(`/user/${id}`, 'PUT', userData, token);
    },

    deleteUser: async (id, token) => {
        return fetchApi(`/user/${id}`, 'DELETE', null, token);
    },
};

// Admin API
export const adminApi = {
    getAdminPanel: async (token) => {
        return fetchApi('/admin/pannello', 'GET', null, token);
    },

    getAllUsers: async (token) => {
        return fetchApi('/admin', 'GET', null, token);
    },

    getUserById: async (id, token) => {
        return fetchApi(`/admin/${id}`, 'GET', null, token);
    },
};

// TierList API
export const tierListApi = {
    getAllTierLists: async () => {
        return fetchApi('/api/tierlists');
    },

    getTierListById: async (id) => {
        return fetchApi(`/api/tierlists/${id}`);
    },

    createTierList: async (tierListData, token) => {
        return fetchApi('/api/tierlists', 'POST', tierListData, token);
    },

    updateTierList: async (id, tierListData, token) => {
        return fetchApi(`/api/tierlists/${id}`, 'PUT', tierListData, token);
    },

    deleteTierList: async (id, token) => {
        return fetchApi(`/api/tierlists/${id}`, 'DELETE', null, token);
    },

    getTierListsByUserId: async (userId) => {
        return fetchApi(`/api/tierlists/by-user/${userId}`);
    },

    searchTierListByTitle: async (title) => {
        return fetchApi(`/api/tierlists/search-by-title?titolo=${encodeURIComponent(title)}`);
    },
};

// Tier API
export const tierApi = {
    getAllTiers: async () => {
        return fetchApi('/api/tiers');
    },

    getTierById: async (id) => {
        return fetchApi(`/api/tiers/${id}`);
    },

    createTier: async (tierData, token) => {
        return fetchApi('/api/tiers', 'POST', tierData, token);
    },

    updateTier: async (id, tierData, token) => {
        return fetchApi(`/api/tiers/${id}`, 'PUT', tierData, token);
    },

    deleteTier: async (id, token) => {
        return fetchApi(`/api/tiers/${id}`, 'DELETE', null, token);
    },
};

// Element API
export const elementApi = {
    getAllElements: async () => {
        return fetchApi('/api/elements');
    },

    getElementById: async (id) => {
        return fetchApi(`/api/elements/${id}`);
    },

    searchElementsByName: async (name) => {
        return fetchApi(`/api/elements/search?name=${encodeURIComponent(name)}`);
    },

    createElement: async (elementData, token) => {
        return fetchApi('/api/elements', 'POST', elementData, token);
    },

    deleteElement: async (id, token) => {
        return fetchApi(`/api/elements/${id}`, 'DELETE', null, token);
    },
};

// Category API
export const categoryApi = {
    getAllCategories: async () => {
        return fetchApi('/api/categories');
    },

    getCategoryById: async (id) => {
        return fetchApi(`/api/categories/${id}`);
    },

    getCategoryByName: async (name) => {
        return fetchApi(`/api/categories/by-name?categoryName=${encodeURIComponent(name)}`);
    },

    createCategory: async (categoryData, token) => {
        return fetchApi('/api/categories', 'POST', categoryData, token);
    },

    updateCategory: async (id, categoryData, token) => {
        return fetchApi(`/api/categories/${id}`, 'PUT', categoryData, token);
    },

    deleteCategory: async (id, token) => {
        return fetchApi(`/api/categories/${id}`, 'DELETE', null, token);
    },
    // NUOVI METODI per Category
    getElementsByCategory: async (categoryId) => {
        return fetchApi(`/api/elements/by-category/${categoryId}`);
    },

    getUnassignedElementsByCategory: async (categoryId) => {
        return fetchApi(`/api/elements/by-category/${categoryId}/unassigned`);
    },

    getElementsByCategoryAndTier: async (categoryId, tierId) => {
        return fetchApi(`/api/elements/by-category/${categoryId}/tier/${tierId}`);
    },

    countElementsByCategory: async (categoryId) => {
        return fetchApi(`/api/elements/by-category/${categoryId}/count`);
    },

    createElementWithCategory: async (name, imageUrl, categoryId, token) => {
        const params = new URLSearchParams({
            name,
            imageUrl,
            categoryId: categoryId.toString()
        });
        return fetchApi(`/api/elements/create?${params}`, 'POST', null, token);
    },

    assignElementToTier: async (elementId, tierId, token) => {
        return fetchApi(`/api/elements/${elementId}/assign-tier/${tierId}`, 'PUT', null, token);
    },

    removeElementFromTier: async (elementId, token) => {
        return fetchApi(`/api/elements/${elementId}/remove-from-tier`, 'PUT', null, token);
    }
};

export default {
    authApi,
    publicApi,
    userApi,
    adminApi,
    tierListApi,
    tierApi,
    elementApi,
    categoryApi,
};