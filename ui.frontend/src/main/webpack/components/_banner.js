const toPixelValue = (value) => {
    const numberValue = Number.parseFloat(value);
    return Number.isFinite(numberValue) ? `${numberValue}px` : '';
};

const toWidthPixelValue = (value) => {
    const numberValue = Number.parseFloat(value);
    return Number.isFinite(numberValue) && numberValue >= 320 ? `${numberValue}px` : '';
};

const getAlignment = (content) => {
    const banner = content.closest('.banner');
    return banner ? window.getComputedStyle(banner).textAlign : 'left';
};

const alignElement = (element, alignment) => {
    if (alignment === 'center') {
        element.style.marginLeft = 'auto';
        element.style.marginRight = 'auto';
        return;
    }

    if (alignment === 'right' || alignment === 'end') {
        element.style.marginLeft = 'auto';
        element.style.marginRight = '0';
        return;
    }

    element.style.marginLeft = '0';
    element.style.marginRight = 'auto';
};

const setTranslate = (element, xValue, yValue) => {
    const x = toPixelValue(xValue) || '0px';
    const y = toPixelValue(yValue) || '0px';

    if (x === '0px' && y === '0px') {
        element.style.removeProperty('transform');
        return;
    }

    element.style.transform = `translate(${x}, ${y})`;
};

const applyBannerLayout = () => {
    document.querySelectorAll('.banner__content').forEach((content) => {
        const alignment = getAlignment(content);
        const {
            bannerButtonOffsetX,
            bannerButtonOffsetY,
            bannerContentMaxWidth,
            bannerContentOffsetX,
            bannerContentOffsetY,
            bannerTextMaxWidth,
        } = content.dataset;

        const contentMaxWidth = toWidthPixelValue(bannerContentMaxWidth);
        if (contentMaxWidth) {
            content.style.maxWidth = contentMaxWidth;
            content.style.setProperty('--banner-content-max-width', contentMaxWidth);
            alignElement(content, alignment);
        } else {
            content.style.removeProperty('max-width');
            content.style.removeProperty('--banner-content-max-width');
        }

        setTranslate(content, bannerContentOffsetX, bannerContentOffsetY);

        const textMaxWidth = toWidthPixelValue(bannerTextMaxWidth);
        if (textMaxWidth) {
            content.style.setProperty('--banner-text-max-width', textMaxWidth);
        } else {
            content.style.removeProperty('--banner-text-max-width');
        }

        content.querySelectorAll('.text, .cmp-text').forEach((textElement) => {
            if (textMaxWidth) {
                textElement.style.maxWidth = textMaxWidth;
                alignElement(textElement, alignment);
            } else {
                textElement.style.removeProperty('max-width');
            }
        });

        content.querySelectorAll('.button .cmp-button, .button a, .button button').forEach((buttonElement) => {
            setTranslate(buttonElement, bannerButtonOffsetX, bannerButtonOffsetY);
        });
    });
};

if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', applyBannerLayout);
} else {
    applyBannerLayout();
}

document.addEventListener('foundation-contentloaded', applyBannerLayout);
