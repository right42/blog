let $tag = 
(function(){
    return {
        '_tagIds' : [],
        'render' : 
        function (res) {
            let tagsDiv = document.createElement('div');
            res.content.forEach((e) => {
                let el = document.createElement('span');
                el.textContent = e.name;
                el.setAttribute('data-tag-id', e.id);
                el.classList = 'tag-black m-2 click';
                el.addEventListener('click', this.selectTag.bind(this));
                tagsDiv.appendChild(el);
            });

            clearChild('#tags');

            tags.appendChild(tagsDiv);
        },

        'selectTag' : 
        function ({target : element}){
            if (this.saveTag(element.getAttribute('data-tag-id'))){
                let selectedTag = document.querySelector('#selectedTags');
                let tag = document.createElement('span');
                tag.textContent = element.textContent + ' x ';
                tag.classList = 'tag-black m-2 click';
                tag.addEventListener('click', this.removeTag.bind(this));
                selectedTag.appendChild(tag);
            }
        },
        'addTag' : function(tagName, tagId){
            if (this.saveTag(tagId)){
                let selectedTag = document.querySelector('#selectedTags');
                let tag = document.createElement('span');
                tag.textContent = tagName + ' x ';
                tag.classList = 'tag-black m-2 click';
                tag.addEventListener('click', this.removeTag.bind(this));
                selectedTag.appendChild(tag);
            }
        },
        'saveTag' : function (tagId){
            if(!this._tagIds.includes(tagId)){
                this._tagIds.push(tagId);
                return this._tagIds;
            }
            return 0;
        },

        'removeTag' : 
        function ({target : element}){
            let tagId = element.getAttribute('data-tag-id');
            this._tagIds.splice(this._tagIds.indexOf(tagId), 1);
            element.remove();
        }
    };
})();