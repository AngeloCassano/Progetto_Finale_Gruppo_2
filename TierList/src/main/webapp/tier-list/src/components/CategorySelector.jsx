import React, { useState } from 'react';
import useCategory from '../hooks/useCategory';
import './CategorySelector.css';

const CategorySelector = () => {
  const { 
    categories, 
    selectedCategory, 
    changeCategory, 
    loading, 
    error, 
    refreshCategory,
    getCategoryStats 
  } = useCategory();
  
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');

  const filteredCategories = categories.filter(category =>
    category.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    (category.descrizione && category.descrizione.toLowerCase().includes(searchTerm.toLowerCase()))
  );

  const stats = getCategoryStats();

  const handleCategorySelect = (category) => {
    changeCategory(category);
    setIsDropdownOpen(false);
    setSearchTerm('');
  };

  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  return (
    <div className="category-selector-enhanced">
      <div className="category-selector-header">
        <h3 className="category-selector-title">Categoria Attiva</h3>
        {error && (
          <div className="category-error">
            <span className="error-icon">⚠️</span>
            {error}
            <button onClick={refreshCategory} className="retry-button">
              Riprova
            </button>
          </div>
        )}
      </div>

      {/* Categoria attualmente selezionata */}
      <div className="selected-category-display">
        <div 
          className={`selected-category-card ${isDropdownOpen ? 'open' : ''}`}
          onClick={toggleDropdown}
        >
          <div className="category-info">
            <div className="category-name">
              {selectedCategory?.name || 'Nessuna categoria'}
            </div>
            {selectedCategory?.descrizione && (
              <div className="category-description">
                {selectedCategory.descrizione}
              </div>
            )}
            <div className="category-stats">
              <span className="stat available">
                🟢 {stats.available} disponibili
              </span>
              <span className="stat used">
                🔴 {stats.used} in uso
              </span>
              <span className="stat total">
                📊 {stats.total} totali
              </span>
            </div>
          </div>
          <div className="dropdown-arrow">
            {isDropdownOpen ? '▲' : '▼'}
          </div>
        </div>

        {/* Dropdown delle categorie */}
        {isDropdownOpen && (
          <div className="category-dropdown">
            <div className="search-container">
              <input
                type="text"
                placeholder="Cerca categoria..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="category-search"
                autoFocus
              />
            </div>
            
            <div className="category-list">
              {filteredCategories.length > 0 ? (
                filteredCategories.map(category => (
                  <div
                    key={category.id}
                    onClick={() => handleCategorySelect(category)}
                    className={`category-option ${
                      selectedCategory?.id === category.id ? 'selected' : ''
                    }`}
                    disabled={loading}
                  >
                    <div className="category-option-info">
                      <div className="category-option-name">
                        {category.name}
                      </div>
                      {category.descrizione && (
                        <div className="category-option-description">
                          {category.descrizione}
                        </div>
                      )}
                    </div>
                    {selectedCategory?.id === category.id && (
                      <div className="selected-indicator">✓</div>
                    )}
                  </div>
                ))
              ) : (
                <div className="no-categories">
                  Nessuna categoria trovata per "{searchTerm}"
                </div>
              )}
            </div>
          </div>
        )}
      </div>

      {/* Azioni */}
      <div className="category-actions">
        <button 
          onClick={refreshCategory} 
          className="refresh-button" 
          disabled={loading}
          title="Aggiorna categorie ed elementi"
        >
          {loading ? '⏳' : '🔄'} Aggiorna
        </button>
        
        {selectedCategory && (
          <div className="quick-stats">
            <span className="stat-badge available">
              {stats.available} liberi
            </span>
            <span className="stat-badge used">
              {stats.used} usati
            </span>
          </div>
        )}
      </div>
    </div>
  );
};

export default CategorySelector;